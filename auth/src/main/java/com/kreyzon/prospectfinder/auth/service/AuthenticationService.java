package com.kreyzon.prospectfinder.auth.service;

import com.kreyzon.prospectfinder.auth.jwt.JwtProvider;
import com.kreyzon.prospectfinder.auth.model.AppUser;
import com.kreyzon.prospectfinder.auth.repository.AppUserRepository;
import com.kreyzon.prospectfinder.auth.service.validator.AppUserValidator;
import com.kreyzon.prospectfinder.common.Constant;
import com.kreyzon.prospectfinder.common.dto.AppUserDto;
import com.kreyzon.prospectfinder.common.request.AuthenticationRequest;
import com.kreyzon.prospectfinder.common.request.TokenRequest;
import com.kreyzon.prospectfinder.common.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final AppUserRepository appUserRepository;

    private final AppUserService appUserService;

    private final AppUserValidator appUserValidator;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        log.info("Login request: " + authenticationRequest);

        Optional<AppUser> appUserOptional = appUserRepository.findByEmail(authenticationRequest.email());
        if (appUserOptional.isEmpty())
            return new AuthenticationResponse(Constant.RESULT_NOK, "User does not exist", null);

        AppUser appUser = appUserOptional.get();
        AuthenticationResponse response = appUserValidator.validateUserLogin(appUser);
        if (response.isResultNOK())
            return response;

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.email(), authenticationRequest.password()));
        } catch (BadCredentialsException badCredentialsException) {
            return new AuthenticationResponse(Constant.RESULT_NOK, "Credentials not valid");
        }

        UserDetails userDetails = appUserService.loadUserByUsername(appUser.getEmail());
        String token = jwtProvider.generateToken(userDetails);

        AppUserDto appUserDto = new AppUserDto(appUser.getUserId(), appUser.getEmail(), token);

        return new AuthenticationResponse(Constant.RESULT_OK, "User authenticated", null, appUserDto);
    }

    public AuthenticationResponse validateToken(TokenRequest tokenRequest) {
        log.info("Token validation of: " , tokenRequest);
        String email = null;
        try {
            email = jwtProvider.extractUsername(tokenRequest.token());
        } catch (Exception ex) {
            return new AuthenticationResponse(Constant.RESULT_NOK, "Token not valid");
        }

        AppUser appUser = appUserRepository
                .findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Email not found"));

        AppUserDto appUserDto = new AppUserDto(appUser.getUserId(), email, tokenRequest.token());
        return new AuthenticationResponse(Constant.RESULT_OK, "Token valid", appUserDto);
    }
}
