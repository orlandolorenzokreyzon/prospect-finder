package com.kreyzon.prospectfinder.api.service;

import com.kreyzon.prospectfinder.api.model.Session;
import com.kreyzon.prospectfinder.api.repository.SessionRepository;
import com.kreyzon.prospectfinder.api.request.SessionRequest;
import com.kreyzon.prospectfinder.api.response.SessionResponse;
import com.kreyzon.prospectfinder.common.Constant;
import com.kreyzon.prospectfinder.common.HttpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class SessionService {
    private final LoginService loginService;

    private final RestTemplate restTemplate;

    private final SessionRepository sessionRepository;

    private final EndpointService endpointService;

    public Session startSession(SessionRequest sessionRequest) {
        HttpHeaders httpHeaders = HttpUtils.generateHttpHeaders(loginService.login());

        HttpEntity<SessionRequest> requestHttpEntity = new HttpEntity<>(sessionRequest, httpHeaders);

        ResponseEntity<SessionResponse> response = null;
        try {
            response = restTemplate
                    .postForEntity(
                            endpointService.findById(Constant.X_EMAIL_START_SESSION_EP).getFullUrl(),
                            requestHttpEntity,
                            SessionResponse.class
                    );
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalStateException("Error during the start of the session \n" + ex.getMessage());
        }

        Session session = convertSessionResponseToSession(response.getBody(), sessionRequest.userId());
        sessionRepository.save(session);

        return session;
    }

    public Session getSessionInfo(String externalSessionId) {
        if (sessionRepository.findByExternalSessionId(externalSessionId).isEmpty())
            throw new IllegalArgumentException("Id " + externalSessionId + " not found");

        HttpHeaders httpHeaders = HttpUtils.generateHttpHeaders(loginService.login());

        HttpEntity<SessionResponse> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<SessionResponse[]> response = null;
        try {
            response = restTemplate
                    .exchange(
                            endpointService.findById(Constant.X_EMAIL_GET_SESSION_EP).getFullUrl(),
                            HttpMethod.GET,
                            entity,
                            SessionResponse[].class
                    );
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalStateException("Error during the retrieving of the session \n" + ex.getMessage());
        }

        Session session = convertSessionResponseToSession(response.getBody()[0], "NR");
        return session;
    }

    private Session convertSessionResponseToSession(SessionResponse response, String userId) {
        if (response.getMaximum_emails() == null)
            response.setMaximum_emails("0");
        if (response.getScraped_emails() == null)
            response.setScraped_emails("0");

        // TODO userId = prospect finder userId, so I need to delete the second arg of this method

        Session session = Session
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

        return session;
    }

    private Integer generateId() {
        Integer id = sessionRepository.getLastId();
        if (id == null)
            return 1;

        return id += 1;
    }
}
