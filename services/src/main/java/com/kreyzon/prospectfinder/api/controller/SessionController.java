package com.kreyzon.prospectfinder.api.controller;

import com.kreyzon.prospectfinder.api.model.Session;
import com.kreyzon.prospectfinder.api.request.SessionRequest;
import com.kreyzon.prospectfinder.api.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prospectfinder/api/v1/services/business/session")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @GetMapping("/{externalSessionId}")
    public ResponseEntity<Session> info(@PathVariable("externalSessionId") String externalSessionId) {
        return ResponseEntity.ok(sessionService.getSessionInfo(externalSessionId));
    }

    @PostMapping
    public ResponseEntity<Session> start(@RequestBody SessionRequest sessionRequest) {
        return ResponseEntity.ok(sessionService.startSession(sessionRequest));
    }
}