package com.kreyzon.prospectfinder.api.service;

import com.kreyzon.prospectfinder.api.model.Setup;
import com.kreyzon.prospectfinder.api.request.LoginRequest;
import com.kreyzon.prospectfinder.api.response.LoginResponse;
import com.kreyzon.prospectfinder.common.Constant;
import com.kreyzon.prospectfinder.common.HttpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final SetupService setupService;

    private final EndpointService endpointService;

    @Qualifier("outside")
    private final RestTemplate restTemplate;



    public String login() {
        Setup setup = setupService.getSetup();

        HttpHeaders httpHeaders = HttpUtils.generateHttpHeaders("");

        LoginRequest loginRequest = new LoginRequest(setup.getUsername(), setup.getPassword());

        HttpEntity<LoginRequest> requestHttpEntity = new HttpEntity<LoginRequest>(loginRequest, httpHeaders);

        ResponseEntity<LoginResponse> response = null;
        try {
            response = restTemplate
                    .postForEntity(
                            endpointService.findById(Constant.X_EMAIL_AUTH_TOKEN_EP).getFullUrl(),
                            requestHttpEntity,
                            LoginResponse.class
                    );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response.getBody().getToken();
    }
}
