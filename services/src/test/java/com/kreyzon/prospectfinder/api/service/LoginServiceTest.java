package com.kreyzon.prospectfinder.api.service;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.kreyzon.prospectfinder.api.model.Endpoint;
import com.kreyzon.prospectfinder.api.model.Setup;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {LoginService.class})
@ExtendWith(SpringExtension.class)
class LoginServiceTest {
    @MockBean
    private EndpointService endpointService;

    @Autowired
    private LoginService loginService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private SetupService setupService;

    /**
     * Method under test: {@link LoginService#login()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLogin() throws RestClientException {
        Setup setup = new Setup();
        setup.setAvailableCredits(1);
        setup.setId(1);
        setup.setPassword("Password123!");
        setup.setUsername("lorenzo.orlando724@gmail.com");
        when(setupService.getSetup()).thenReturn(setup);

        Endpoint endpoint = new Endpoint();
        endpoint.setDomain("Domain");
        endpoint.setFullUrl("https://app.xemailextractor.com/auth-token/");
        endpoint.setId("42");
        when(endpointService.findById((String) any())).thenReturn(endpoint);
        when(restTemplate.postForEntity((String) any(), (Object) any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        loginService.login();
    }
}

