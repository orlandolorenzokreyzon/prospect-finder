package com.kreyzon.prospectfinder.api.controller;

import com.kreyzon.prospectfinder.api.model.BusinessSession;
import com.kreyzon.prospectfinder.api.request.BusinessSessionRequest;
import com.kreyzon.prospectfinder.api.service.BusinessSessionService;
import com.kreyzon.prospectfinder.common.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prospectfinder/api/v1/services/business/session")
public class BusinessSessionController {
    @Autowired
    private BusinessSessionService businessSessionService;

    @GetMapping
    public ResponseEntity<GenericResponse> findAll() {
        return ResponseEntity.ok(businessSessionService.findAllSessions());
    }

    @GetMapping("/{externalSessionId}")
    public ResponseEntity<GenericResponse> info(@PathVariable("externalSessionId") String externalSessionId) {
        return ResponseEntity.ok(businessSessionService.getSessionInfo(externalSessionId));
    }

    @PostMapping
    public ResponseEntity<GenericResponse> start(@RequestBody BusinessSessionRequest businessSessionRequest) {
        return ResponseEntity.ok(businessSessionService.startSession(businessSessionRequest));
    }
}