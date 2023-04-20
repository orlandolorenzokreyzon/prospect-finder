package com.kreyzon.prospectfinder.auth.controller;

import com.kreyzon.prospectfinder.auth.service.AppUserService;
import com.kreyzon.prospectfinder.common.request.UserCreateRequest;
import com.kreyzon.prospectfinder.common.response.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prospectfinder/api/v1/auth/user")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<GenericResponse> create(@RequestBody UserCreateRequest userCreateRequest) {
        return ResponseEntity.ok(appUserService.create(userCreateRequest));
    }
}
