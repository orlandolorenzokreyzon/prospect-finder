package com.kreyzon.prospectfinder.auth.utils;

import com.kreyzon.prospectfinder.auth.model.AppUser;
import com.kreyzon.prospectfinder.auth.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class IdUtils {
    private final AppUserRepository appUserRepository;

    public Integer generateAppUserId() {
        Optional<AppUser> appUserOptional = appUserRepository.findTopByOrderByIdDesc();
        if (appUserOptional.isEmpty())
            return 1;

        return appUserOptional.get().getUserId() + 1;
    }
}
