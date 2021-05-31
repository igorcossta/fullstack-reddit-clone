package com.reddit.spring.controller;

import com.reddit.spring.dto.AuthenticationResponse;
import com.reddit.spring.dto.LoginRequest;
import com.reddit.spring.dto.RegisterRequest;
import com.reddit.spring.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<String>("registration success", HttpStatus.OK);
    }

    @GetMapping(path = "/verify-token/{token}")
    public ResponseEntity<String> verifyToken(@PathVariable String token) {
        authService.verifyToken(token);
        return new ResponseEntity<String>("account verified", HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
