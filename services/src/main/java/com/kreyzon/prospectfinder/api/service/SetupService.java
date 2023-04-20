package com.kreyzon.prospectfinder.api.service;

import com.kreyzon.prospectfinder.api.model.Setup;
import com.kreyzon.prospectfinder.api.repository.SetupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SetupService {

    private final SetupRepository setupRepository;

    public Setup getSetup() {
        return setupRepository.findAll().get(0);
    }
}
