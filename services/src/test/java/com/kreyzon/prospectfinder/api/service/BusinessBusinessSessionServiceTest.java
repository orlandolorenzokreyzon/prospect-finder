package com.kreyzon.prospectfinder.api.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kreyzon.prospectfinder.api.model.Endpoint;
import com.kreyzon.prospectfinder.api.model.Setup;
import com.kreyzon.prospectfinder.api.repository.EndpointRepository;
import com.kreyzon.prospectfinder.api.repository.BussinessSessionRepository;
import com.kreyzon.prospectfinder.api.repository.SetupRepository;
import com.kreyzon.prospectfinder.api.request.BusinessSessionRequest;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {BusinessSessionService.class})
@ExtendWith(SpringExtension.class)
class BusinessBusinessSessionServiceTest {
    @MockBean
    private EndpointService endpointService;

    @MockBean
    private BusinessLoginService businessLoginService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private BussinessSessionRepository bussinessSessionRepository;

    @Autowired
    private BusinessSessionService businessSessionService;

    /**
     * Method under test: {@link BusinessSessionService#startSession(BusinessSessionRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testStartSession() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "response" is null
        //       at com.kreyzon.prospectfinder.api.service.SessionService.startSession(SessionService.java:50)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        //       at jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        //       at jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        //       at jdk.internal.util.Preconditions.checkIndex(Preconditions.java:266)
        //       at java.util.Objects.checkIndex(Objects.java:359)
        //       at java.util.ArrayList.get(ArrayList.java:427)
        //       at com.kreyzon.prospectfinder.api.service.SetupService.getSetup(SetupService.java:16)
        //       at com.kreyzon.prospectfinder.api.service.LoginService.login(LoginService.java:30)
        //       at com.kreyzon.prospectfinder.api.service.SessionService.startSession(SessionService.java:33)
        //   See https://diff.blue/R013 to resolve this issue.

        SetupRepository setupRepository = mock(SetupRepository.class);
        when(setupRepository.findAll()).thenReturn(new ArrayList<>());
        SetupService setupService = new SetupService(setupRepository);
        BusinessLoginService businessLoginService = new BusinessLoginService(setupService, new EndpointService(mock(EndpointRepository.class)),
                mock(RestTemplate.class));

        RestTemplate restTemplate = mock(RestTemplate.class);
        BussinessSessionRepository bussinessSessionRepository = mock(BussinessSessionRepository.class);
        BusinessSessionService businessSessionService = new BusinessSessionService(businessLoginService, restTemplate, bussinessSessionRepository,
                new EndpointService(mock(EndpointRepository.class)));
        businessSessionService
                .startSession(new BusinessSessionRequest("Name", "Scrape info", "Scrape type", "jane.doe@example.org"));
    }

    /**
     * Method under test: {@link BusinessSessionService#startSession(BusinessSessionRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testStartSession2() throws RestClientException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "response" is null
        //       at com.kreyzon.prospectfinder.api.service.SessionService.startSession(SessionService.java:50)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "response" is null
        //       at com.kreyzon.prospectfinder.api.service.LoginService.login(LoginService.java:50)
        //       at com.kreyzon.prospectfinder.api.service.SessionService.startSession(SessionService.java:33)
        //   See https://diff.blue/R013 to resolve this issue.

        Setup setup = new Setup();
        setup.setBusinessAvailableCredits(1);
        setup.setId(1);
        setup.setBusinessPassword("iloveyou");
        setup.setBusinessUsername("janedoe");

        ArrayList<Setup> setupList = new ArrayList<>();
        setupList.add(setup);
        SetupRepository setupRepository = mock(SetupRepository.class);
        when(setupRepository.findAll()).thenReturn(setupList);
        SetupService setupService = new SetupService(setupRepository);

        Endpoint endpoint = new Endpoint();
        endpoint.setDomain("Domain");
        endpoint.setFullUrl("https://example.org/example");
        endpoint.setId("42");
        EndpointRepository endpointRepository = mock(EndpointRepository.class);
        when(endpointRepository.findById((String) any())).thenReturn(Optional.of(endpoint));
        EndpointService endpointService = new EndpointService(endpointRepository);
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.postForEntity((String) any(), (Object) any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        BusinessLoginService businessLoginService = new BusinessLoginService(setupService, endpointService, restTemplate);

        RestTemplate restTemplate1 = mock(RestTemplate.class);
        BussinessSessionRepository bussinessSessionRepository = mock(BussinessSessionRepository.class);
        BusinessSessionService businessSessionService = new BusinessSessionService(businessLoginService, restTemplate1, bussinessSessionRepository,
                new EndpointService(mock(EndpointRepository.class)));
        businessSessionService
                .startSession(new BusinessSessionRequest("Name", "Scrape info", "Scrape type", "jane.doe@example.org"));
    }

    /**
     * Method under test: {@link BusinessSessionService#startSession(BusinessSessionRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testStartSession3() throws RestClientException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "response" is null
        //       at com.kreyzon.prospectfinder.api.service.SessionService.startSession(SessionService.java:50)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "response" is null
        //       at com.kreyzon.prospectfinder.api.service.LoginService.login(LoginService.java:50)
        //       at com.kreyzon.prospectfinder.api.service.SessionService.startSession(SessionService.java:33)
        //   See https://diff.blue/R013 to resolve this issue.

        Setup setup = new Setup();
        setup.setBusinessAvailableCredits(1);
        setup.setId(1);
        setup.setBusinessPassword("iloveyou");
        setup.setBusinessUsername("janedoe");
        SetupService setupService = Mockito.mock(SetupService.class);
        when(setupService.getSetup()).thenReturn(setup);
        EndpointRepository endpointRepository = mock(EndpointRepository.class);
        when(endpointRepository.findById((String) any())).thenReturn(Optional.empty());
        EndpointService endpointService = new EndpointService(endpointRepository);
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.postForEntity((String) any(), (Object) any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        BusinessLoginService businessLoginService = new BusinessLoginService(setupService, endpointService, restTemplate);

        RestTemplate restTemplate1 = mock(RestTemplate.class);
        BussinessSessionRepository bussinessSessionRepository = mock(BussinessSessionRepository.class);
        BusinessSessionService businessSessionService = new BusinessSessionService(businessLoginService, restTemplate1, bussinessSessionRepository,
                new EndpointService(mock(EndpointRepository.class)));
        businessSessionService
                .startSession(new BusinessSessionRequest( "Name", "Scrape info", "Scrape type", "jane.doe@example.org"));
    }

    /**
     * Method under test: {@link BusinessSessionService#startSession(BusinessSessionRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testStartSession4() throws RestClientException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "response" is null
        //       at com.kreyzon.prospectfinder.api.service.SessionService.startSession(SessionService.java:50)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "response" is null
        //       at com.kreyzon.prospectfinder.api.service.LoginService.login(LoginService.java:50)
        //       at com.kreyzon.prospectfinder.api.service.SessionService.startSession(SessionService.java:33)
        //   See https://diff.blue/R013 to resolve this issue.

        Setup setup = new Setup();
        setup.setBusinessAvailableCredits(1);
        setup.setId(1);
        setup.setBusinessPassword("iloveyou");
        setup.setBusinessUsername("janedoe");
        SetupService setupService = Mockito.mock(SetupService.class);
        when(setupService.getSetup()).thenReturn(setup);
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.postForEntity((String) any(), (Object) any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        BusinessLoginService businessLoginService = new BusinessLoginService(setupService, null, restTemplate);

        RestTemplate restTemplate1 = mock(RestTemplate.class);
        BussinessSessionRepository bussinessSessionRepository = mock(BussinessSessionRepository.class);
        BusinessSessionService businessSessionService = new BusinessSessionService(businessLoginService, restTemplate1, bussinessSessionRepository,
                new EndpointService(mock(EndpointRepository.class)));
        businessSessionService
                .startSession(new BusinessSessionRequest("Pepe", "Scrape info", "Scrape type", "jane.doe@example.org"));
    }

    /**
     * Method under test: {@link BusinessSessionService#startSession(BusinessSessionRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testStartSession5() throws RestClientException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "response" is null
        //       at com.kreyzon.prospectfinder.api.service.SessionService.startSession(SessionService.java:50)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "response" is null
        //       at com.kreyzon.prospectfinder.api.service.LoginService.login(LoginService.java:50)
        //       at com.kreyzon.prospectfinder.api.service.SessionService.startSession(SessionService.java:33)
        //   See https://diff.blue/R013 to resolve this issue.

        Setup setup = new Setup();
        setup.setBusinessAvailableCredits(1);
        setup.setId(1);
        setup.setBusinessPassword("iloveyou");
        setup.setBusinessUsername("janedoe");
        SetupService setupService = Mockito.mock(SetupService.class);
        when(setupService.getSetup()).thenReturn(setup);

        Endpoint endpoint = new Endpoint();
        endpoint.setDomain("Domain");
        endpoint.setFullUrl("https://example.org/example");
        endpoint.setId("42");
        EndpointService endpointService = Mockito.mock(EndpointService.class);
        when(endpointService.findById((String) any())).thenReturn(endpoint);
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.postForEntity((String) any(), (Object) any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        BusinessLoginService businessLoginService = new BusinessLoginService(setupService, endpointService, restTemplate);

        RestTemplate restTemplate1 = mock(RestTemplate.class);
        BussinessSessionRepository bussinessSessionRepository = mock(BussinessSessionRepository.class);
        BusinessSessionService businessSessionService = new BusinessSessionService(businessLoginService, restTemplate1, bussinessSessionRepository,
                new EndpointService(mock(EndpointRepository.class)));
        businessSessionService
                .startSession(new BusinessSessionRequest("Name", "Scrape info", "Scrape type", "jane.doe@example.org"));
    }

    /**
     * Method under test: {@link BusinessSessionService#startSession(BusinessSessionRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testStartSession6() throws RestClientException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "response" is null
        //       at com.kreyzon.prospectfinder.api.service.SessionService.startSession(SessionService.java:50)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "response" is null
        //       at com.kreyzon.prospectfinder.api.service.SessionService.startSession(SessionService.java:50)
        //   See https://diff.blue/R013 to resolve this issue.

        BusinessLoginService businessLoginService = Mockito.mock(BusinessLoginService.class);
        when(businessLoginService.login()).thenReturn("Login");
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.postForEntity((String) any(), (Object) any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        Endpoint endpoint = new Endpoint();
        endpoint.setDomain("Domain");
        endpoint.setFullUrl("https://example.org/example");
        endpoint.setId("42");
        EndpointRepository endpointRepository = mock(EndpointRepository.class);
        when(endpointRepository.findById((String) any())).thenReturn(Optional.of(endpoint));
        BusinessSessionService businessSessionService = new BusinessSessionService(businessLoginService, restTemplate, mock(BussinessSessionRepository.class),
                new EndpointService(endpointRepository));
        businessSessionService
                .startSession(new BusinessSessionRequest("Name", "Scrape info", "Scrape type", "jane.doe@example.org"));
    }

    /**
     * Method under test: {@link BusinessSessionService#startSession(BusinessSessionRequest)}
     */
    @Test
    void testStartSession7() throws RestClientException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "response" is null
        //       at com.kreyzon.prospectfinder.api.service.SessionService.startSession(SessionService.java:50)
        //   See https://diff.blue/R013 to resolve this issue.

        BusinessLoginService businessLoginService = Mockito.mock(BusinessLoginService.class);
        when(businessLoginService.login()).thenReturn("Login");
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.postForEntity((String) any(), (Object) any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        EndpointRepository endpointRepository = mock(EndpointRepository.class);
        when(endpointRepository.findById((String) any())).thenReturn(Optional.empty());
        BusinessSessionService businessSessionService = new BusinessSessionService(businessLoginService, restTemplate, mock(BussinessSessionRepository.class),
                new EndpointService(endpointRepository));
        assertThrows(IllegalStateException.class, () -> businessSessionService
                .startSession(new BusinessSessionRequest( "Name", "Scrape info", "Scrape type", "jane.doe@example.org")));
        verify(businessLoginService).login();
        verify(endpointRepository).findById((String) any());
    }

    /**
     * Method under test: {@link BusinessSessionService#startSession(BusinessSessionRequest)}
     */
    @Test
    void testStartSession8() throws RestClientException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "response" is null
        //       at com.kreyzon.prospectfinder.api.service.SessionService.startSession(SessionService.java:50)
        //   See https://diff.blue/R013 to resolve this issue.

        BusinessLoginService businessLoginService = Mockito.mock(BusinessLoginService.class);
        when(businessLoginService.login()).thenReturn("Login");
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.postForEntity((String) any(), (Object) any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        BusinessSessionService businessSessionService = new BusinessSessionService(businessLoginService, restTemplate, mock(BussinessSessionRepository.class),
                null);
        assertThrows(IllegalStateException.class, () -> businessSessionService
                .startSession(new BusinessSessionRequest("Name", "Scrape info", "Scrape type", "jane.doe@example.org")));
        verify(businessLoginService).login();
    }

    /**
     * Method under test: {@link BusinessSessionService#startSession(BusinessSessionRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testStartSession9() throws RestClientException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "response" is null
        //       at com.kreyzon.prospectfinder.api.service.SessionService.startSession(SessionService.java:50)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "response" is null
        //       at com.kreyzon.prospectfinder.api.service.SessionService.startSession(SessionService.java:50)
        //   See https://diff.blue/R013 to resolve this issue.

        BusinessLoginService businessLoginService = Mockito.mock(BusinessLoginService.class);
        when(businessLoginService.login()).thenReturn("Login");
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.postForEntity((String) any(), (Object) any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        Endpoint endpoint = new Endpoint();
        endpoint.setDomain("Domain");
        endpoint.setFullUrl("https://example.org/example");
        endpoint.setId("42");
        EndpointService endpointService = Mockito.mock(EndpointService.class);
        when(endpointService.findById((String) any())).thenReturn(endpoint);
        BusinessSessionService businessSessionService = new BusinessSessionService(businessLoginService, restTemplate, mock(BussinessSessionRepository.class),
                endpointService);
        businessSessionService
                .startSession(new BusinessSessionRequest( "Name", "Scrape info", "Scrape type", "jane.doe@example.org"));
    }

    /**
     * Method under test: {@link BusinessSessionService#getSessionInfo(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetSessionInfo() throws RestClientException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "response" is null
        //       at com.kreyzon.prospectfinder.api.service.SessionService.getSessionInfo(SessionService.java:78)
        //   See https://diff.blue/R013 to resolve this issue.

        when(businessLoginService.login()).thenReturn("Login");
        when(restTemplate.exchange((String) any(), (HttpMethod) any(), (HttpEntity<Object>) any(), (Class<Object>) any(),
                (Object[]) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        when(bussinessSessionRepository.findByExternalSessionId((String) any())).thenReturn("42");

        Endpoint endpoint = new Endpoint();
        endpoint.setDomain("Domain");
        endpoint.setFullUrl("https://example.org/example");
        endpoint.setId("42");
        when(endpointService.findById((String) any())).thenReturn(endpoint);
        businessSessionService.getSessionInfo("42");
    }

    /**
     * Method under test: {@link BusinessSessionService#getSessionInfo(String)}
     */
    @Test
    void testGetSessionInfo2() throws RestClientException {
        when(businessLoginService.login()).thenReturn("Login");
        when(restTemplate.exchange((String) any(), (HttpMethod) any(), (HttpEntity<Object>) any(), (Class<Object>) any(),
                (Object[]) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        when(bussinessSessionRepository.findByExternalSessionId((String) any())).thenReturn("");

        Endpoint endpoint = new Endpoint();
        endpoint.setDomain("Domain");
        endpoint.setFullUrl("https://example.org/example");
        endpoint.setId("42");
        when(endpointService.findById((String) any())).thenReturn(endpoint);
        assertThrows(IllegalArgumentException.class, () -> businessSessionService.getSessionInfo("42"));
        verify(bussinessSessionRepository).findByExternalSessionId((String) any());
    }
}

