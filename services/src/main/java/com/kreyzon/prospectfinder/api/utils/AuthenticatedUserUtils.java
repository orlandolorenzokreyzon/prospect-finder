package com.kreyzon.prospectfinder.api.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticatedUserUtils {

    public static final String getAppUserId() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        return authentication.getName();
    }
}
