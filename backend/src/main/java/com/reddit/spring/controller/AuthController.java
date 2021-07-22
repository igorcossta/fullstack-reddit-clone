package com.reddit.spring.controller;

import com.reddit.spring.dto.LoginResponse;
import com.reddit.spring.dto.UsernameAndPasswordRequest;
import com.reddit.spring.jwt.JwtUtils;
import com.reddit.spring.mapper.UserMapper;
import com.reddit.spring.model.User;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/signin")
@AllArgsConstructor
public class AuthController {
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager manager;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<LoginResponse> signin(@RequestBody @Valid UsernameAndPasswordRequest request) {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            );
            Authentication authenticate = manager.authenticate(authentication);
            User principal = (User) authenticate.getPrincipal();

            String token = JwtUtils.createToken(principal);

            LoginResponse loginResponse = userMapper.mapUserToDto(principal, token);

            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException(ex.getMessage());
        }
    }
}
