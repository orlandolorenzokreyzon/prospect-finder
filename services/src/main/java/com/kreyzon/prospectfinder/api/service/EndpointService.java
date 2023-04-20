package com.kreyzon.prospectfinder.api.service;

import com.kreyzon.prospectfinder.api.model.Endpoint;
import com.kreyzon.prospectfinder.api.repository.EndpointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EndpointService {

    private final EndpointRepository endpointRepository;

    public Endpoint findById(String id) {
        return endpointRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("Id not found"));
    }
}
