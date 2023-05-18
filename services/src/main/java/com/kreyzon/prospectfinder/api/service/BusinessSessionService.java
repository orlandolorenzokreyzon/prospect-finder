package com.kreyzon.prospectfinder.api.service;

import com.kreyzon.prospectfinder.api.model.BusinessSession;
import com.kreyzon.prospectfinder.api.repository.BussinessSessionRepository;
import com.kreyzon.prospectfinder.api.request.BusinessSessionRequest;
import com.kreyzon.prospectfinder.api.response.BusinessSessionResponse;
import com.kreyzon.prospectfinder.api.utils.AuthenticatedUserUtils;
import com.kreyzon.prospectfinder.common.Constant;
import com.kreyzon.prospectfinder.common.HttpUtils;
import com.kreyzon.prospectfinder.common.response.GenericResponse;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.description.type.TypeList;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.asn1.gnu.GNUObjectIdentifiers;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BusinessSessionService {
    private final BusinessLoginService businessLoginService;

    private final RestTemplate restTemplate;

    private final BussinessSessionRepository bussinessSessionRepository;

    private final EndpointService endpointService;

    /**
     * TODO: Call https://app.xemailextractor.com/api/client/ to get all tasks and map them to tasks saved inside internal database
     *
     * @return
     */
    public GenericResponse findAllSessions() {
        String userId = AuthenticatedUserUtils.getAppUserId();

        List<BusinessSession> businessSessionList = bussinessSessionRepository.findByUserId(userId);
        if (businessSessionList.isEmpty())
            return new GenericResponse(Constant.RESULT_NOK, "No sessions available");

        List<BusinessSession> convertedBusinessSessionList = new ArrayList<>(businessSessionList.size());

        for (BusinessSession businessSession : businessSessionList) {
            if (StringUtils.isNotBlank(businessSession.getExternalSessionId())) {
                BusinessSession convertedBusinessSession = (BusinessSession) getSessionInfo(businessSession.getExternalSessionId()).getObject();
                convertedBusinessSession.setId(businessSession.getId());
                convertedBusinessSessionList.add(convertedBusinessSession);
            }
        }

        return new GenericResponse(Constant.RESULT_OK, "List returned", convertedBusinessSessionList);
    }

    public GenericResponse startSession(BusinessSessionRequest businessSessionRequest) {
        HttpHeaders httpHeaders = HttpUtils.generateHttpHeaders(businessLoginService.login());

        HttpEntity<BusinessSessionRequest> requestHttpEntity = new HttpEntity<>(businessSessionRequest, httpHeaders);

        ResponseEntity<BusinessSessionResponse> response = null;
        try {
            response = restTemplate
                    .postForEntity(
                            endpointService.findById(Constant.X_EMAIL_START_SESSION_EP).getFullUrl(),
                            requestHttpEntity,
                            BusinessSessionResponse.class
                    );
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalStateException("Error during the start of the session \n" + ex.getMessage());
        }

        BusinessSession businessSession = convertSessionResponseToSession(response.getBody());
        bussinessSessionRepository.save(businessSession);

        return new GenericResponse(Constant.RESULT_OK, "Session started", businessSession);
    }

    public GenericResponse getSessionInfo(String externalSessionId) {
        if (bussinessSessionRepository.findByExternalSessionId(externalSessionId).isEmpty())
            throw new IllegalArgumentException("Id " + externalSessionId + " not found");

        HttpHeaders httpHeaders = HttpUtils.generateHttpHeaders(businessLoginService.login());

        HttpEntity<BusinessSessionResponse> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<BusinessSessionResponse[]> response = null;
        try {
            response = restTemplate
                    .exchange(
                            endpointService.findById(Constant.X_EMAIL_GET_SESSION_EP).getFullUrl(),
                            HttpMethod.GET,
                            entity,
                            BusinessSessionResponse[].class
                    );
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalStateException("Error during the retrieving of the session \n" + ex.getMessage());
        }

        BusinessSession businessSession = convertSessionResponseToSession(response.getBody()[0]);
        return new GenericResponse(Constant.RESULT_OK, "Session retrieved", businessSession);
    }

    private BusinessSession convertSessionResponseToSession(BusinessSessionResponse response) {
        if (response.getMaximum_emails() == null)
            response.setMaximum_emails("0");
        if (response.getScraped_emails() == null)
            response.setScraped_emails("0");

        String userId = AuthenticatedUserUtils.getAppUserId();

        BusinessSession businessSession = BusinessSession
                .builder()
                .id(generateId())
                .userId(userId)
                .name(response.getName())
                .scrapeInfo(response.getScrape_info())
                .scrapeInfoFile(response.getScrape_info_file())
                .scrapeType(response.getScrape_type())
                .maximumEmails(Integer.valueOf(response.getMaximum_emails()))
                .scrapedEmails(Integer.valueOf(response.getScraped_emails()))
                .downloadSheet(response.getDownload_sheet())
                .status(response.getStatus())
                .externalUserId(String.valueOf(response.getUser()))
                .externalSessionId(response.getId())
                .creationDate(LocalDateTime.now())
                .build();

        return businessSession;
    }

    private Integer generateId() {
        Integer id = bussinessSessionRepository.getLastId();
        if (id == null)
            return 1;

        return id += 1;
    }
}
