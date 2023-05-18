package com.kreyzon.prospectfinder.auth.controller;

import com.kreyzon.prospectfinder.auth.jwt.JwtRequestFilter;
import com.kreyzon.prospectfinder.auth.service.AuthenticationService;
import com.kreyzon.prospectfinder.common.request.AuthenticationRequest;
import com.kreyzon.prospectfinder.common.request.TokenRequest;
import com.kreyzon.prospectfinder.common.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prospectfinder/api/v1/auth/login")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    private final JwtRequestFilter jwtRequestFilter;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.login(authenticationRequest));
    }

    @PostMapping("/validate-token")
    public ResponseEntity<AuthenticationResponse> validateToken(@RequestBody TokenRequest request) {
        return ResponseEntity.ok(authenticationService.validateToken(request));
    }
}