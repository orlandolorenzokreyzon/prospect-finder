package com.kreyzon.prospectfinder.auth.service;

import com.kreyzon.prospectfinder.auth.jwt.JwtProvider;
import com.kreyzon.prospectfinder.auth.model.AppUser;
import com.kreyzon.prospectfinder.auth.repository.AppUserRepository;
import com.kreyzon.prospectfinder.auth.service.validator.AppUserValidator;
import com.kreyzon.prospectfinder.common.Constant;
import com.kreyzon.prospectfinder.common.request.AuthenticationRequest;
import com.kreyzon.prospectfinder.common.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AppUserRepository appUserRepository;

    private final AppUserService appUserService;

    private final AppUserValidator appUserValidator;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        Optional<AppUser> appUserOptional = appUserRepository.findByEmail(authenticationRequest.email());
        if (appUserOptional.isEmpty())
            return new AuthenticationResponse(Constant.RESULT_NOK, "User does not exist", null);

        AppUser appUser = appUserOptional.get();
        AuthenticationResponse response = appUserValidator.validateUserLogin(appUser);
        if (response.isResultNOK())
            return response;

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getEmail(), appUser.getPassword()));
        } catch (BadCredentialsException badCredentialsException) {
            return new AuthenticationResponse(Constant.RESULT_NOK, "Credentials not valid");
        }

        UserDetails userDetails = appUserService.loadUserByUsername(appUser.getEmail());
        String token = jwtProvider.generateToken(userDetails);

        return new AuthenticationResponse(Constant.RESULT_OK, "User authenticated", null, token);
    }
}
