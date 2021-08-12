package com.reddit.spring.controller;

import com.reddit.spring.dto.RegisterRequest;
import com.reddit.spring.service.RegisterService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/register")
@AllArgsConstructor
public class RegisterController {
    private final static Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);
    private final RegisterService registerService;

    @ApiOperation(value = "create account",
            notes = "this endpoint create a new account",
            nickname = "createNewAccount")
    @PostMapping
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest account) {
        registerService.register(account);
        LOGGER.debug("método register executado: " + account);
        return new ResponseEntity<>(CREATED);
    }

    @ApiOperation(value = "confirm account",
            notes = "this endpoint confirm an account previously registered",
            nickname = "confirmAccount")
    @GetMapping("/confirm")
    public ResponseEntity<Void> confirmToken(@RequestParam("token") String token) {
        registerService.confirmToken(token);
        LOGGER.debug("método confirmToken executado: " + token);
        return new ResponseEntity<>(OK);
    }
}
