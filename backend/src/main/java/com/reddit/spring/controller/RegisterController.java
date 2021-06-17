package com.reddit.spring.controller;

import com.reddit.spring.dto.RegisterRequest;
import com.reddit.spring.service.RegisterService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/register")
@AllArgsConstructor
public class RegisterController {
    private final static Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);
    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest account) {
        registerService.register(account);
        LOGGER.debug("método register executado: " + account.toString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/confirm")
    public ResponseEntity<Void> confirmToken(@RequestParam("token") String token) {
        registerService.confirmToken(token);
        LOGGER.debug("método confirmToken executado: " + token);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
