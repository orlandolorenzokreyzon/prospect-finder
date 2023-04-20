package com.kreyzon.prospectfinder.auth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kreyzon.prospectfinder.auth.jwt.JwtProvider;
import com.kreyzon.prospectfinder.auth.model.AppUser;
import com.kreyzon.prospectfinder.auth.repository.AppUserRepository;
import com.kreyzon.prospectfinder.auth.service.validator.AppUserValidator;
import com.kreyzon.prospectfinder.common.request.AuthenticationRequest;
import com.kreyzon.prospectfinder.common.response.AuthenticationResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthenticationService.class})
@ExtendWith(SpringExtension.class)
class AuthenticationServiceTest {
    @MockBean
    private AppUserRepository appUserRepository;

    @MockBean
    private AppUserService appUserService;

    @MockBean
    private AppUserValidator appUserValidator;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    @MockBean
    private JwtProvider jwtProvider;

    /**
     * Method under test: {@link AuthenticationService#login(AuthenticationRequest)}
     */
    @Test
    void testLogin3() {
        when(appUserRepository.findByEmail((String) any())).thenReturn(Optional.empty());
        when(appUserValidator.validateUserLogin((AppUser) any())).thenReturn(new AuthenticationResponse("ABC123"));
        AuthenticationResponse actualLoginResult = authenticationService
                .login(new AuthenticationRequest("jane.doe@example.org", "iloveyou"));
        assertEquals("User does not exist", actualLoginResult.getMessage());
        assertNull(actualLoginResult.getToken());
        assertEquals("NOK", actualLoginResult.getResult());
        verify(appUserRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link AuthenticationService#login(AuthenticationRequest)}
     */
    @Test
    void testLogin4() {
        AppUser appUser = new AppUser();
        appUser.setCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        appUser.setDeleted(true);
        appUser.setEmail("jane.doe@example.org");
        appUser.setEnabled(true);
        appUser.setLastLoginDate(LocalDateTime.of(1, 1, 1, 1, 1));
        appUser.setPassword("iloveyou");
        appUser.setUserId(1);
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(appUserRepository.findByEmail((String) any())).thenReturn(ofResult);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse("NOK", "Not all who wander are lost");

        when(appUserValidator.validateUserLogin((AppUser) any())).thenReturn(authenticationResponse);
        assertSame(authenticationResponse,
                authenticationService.login(new AuthenticationRequest("jane.doe@example.org", "iloveyou")));
        verify(appUserRepository).findByEmail((String) any());
        verify(appUserValidator).validateUserLogin((AppUser) any());
    }

    /**
     * Method under test: {@link AuthenticationService#login(AuthenticationRequest)}
     */
    @Test
    void testLogin5() throws UsernameNotFoundException {
        AppUser appUser = new AppUser();
        appUser.setCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        appUser.setDeleted(true);
        appUser.setEmail("jane.doe@example.org");
        appUser.setEnabled(true);
        appUser.setLastLoginDate(LocalDateTime.of(1, 1, 1, 1, 1));
        appUser.setPassword("iloveyou");
        appUser.setUserId(1);
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(appUserRepository.findByEmail((String) any())).thenReturn(ofResult);
        when(appUserService.loadUserByUsername((String) any()))
                .thenReturn(new User("janedoe", "iloveyou", new ArrayList<>()));
        AuthenticationResponse authenticationResponse = mock(AuthenticationResponse.class);
        when(authenticationResponse.isResultNOK()).thenReturn(true);
        when(appUserValidator.validateUserLogin((AppUser) any())).thenReturn(authenticationResponse);
        authenticationService.login(new AuthenticationRequest("jane.doe@example.org", "iloveyou"));
        verify(appUserRepository).findByEmail((String) any());
        verify(appUserValidator).validateUserLogin((AppUser) any());
        verify(authenticationResponse).isResultNOK();
    }
}

