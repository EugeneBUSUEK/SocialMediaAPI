package com.test.socialmedia.api.controller;

import com.test.socialmedia.api.security.AuthenticationService;
import com.test.socialmedia.model.request.AuthenticationRequest;
import com.test.socialmedia.model.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
//@Tag(name = "authentication", description = "authentication resources")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    //    @Operation(summary = "register user")
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    //    @Operation(summary = "authenticate user")
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}
