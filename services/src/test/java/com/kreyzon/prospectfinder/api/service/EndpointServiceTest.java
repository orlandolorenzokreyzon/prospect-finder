package com.kreyzon.prospectfinder.api.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kreyzon.prospectfinder.api.model.Endpoint;
import com.kreyzon.prospectfinder.api.repository.EndpointRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EndpointService.class})
@ExtendWith(SpringExtension.class)
class EndpointServiceTest {
    @MockBean
    private EndpointRepository endpointRepository;

    @Autowired
    private EndpointService endpointService;

    /**
     * Method under test: {@link EndpointService#findById(String)}
     */
    @Test
    void testFindById() {
        Endpoint endpoint = new Endpoint();
        endpoint.setDomain("Domain");
        endpoint.setFullUrl("https://example.org/example");
        endpoint.setId("42");
        Optional<Endpoint> ofResult = Optional.of(endpoint);
        when(endpointRepository.findById((String) any())).thenReturn(ofResult);
        assertSame(endpoint, endpointService.findById("42"));
        verify(endpointRepository).findById((String) any());
    }

    /**
     * Method under test: {@link EndpointService#findById(String)}
     */
    @Test
    void testFindById2() {
        when(endpointRepository.findById((String) any())).thenReturn(Optional.empty());
        assertThrows(IllegalStateException.class, () -> endpointService.findById("42"));
        verify(endpointRepository).findById((String) any());
    }

    /**
     * Method under test: {@link EndpointService#findById(String)}
     */
    @Test
    void testFindById3() {
        when(endpointRepository.findById((String) any())).thenThrow(new IllegalStateException());
        assertThrows(IllegalStateException.class, () -> endpointService.findById("42"));
        verify(endpointRepository).findById((String) any());
    }
}

