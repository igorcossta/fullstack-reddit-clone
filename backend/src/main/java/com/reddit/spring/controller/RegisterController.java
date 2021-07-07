package com.reddit.spring.controller;

import com.reddit.spring.dto.Error;
import com.reddit.spring.dto.RegisterRequest;
import com.reddit.spring.service.RegisterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "${RegisterController.register.value}", notes = "${RegisterController.register.notes}", nickname = "${RegisterController.register.nickname}")
    @ApiResponses({
            @ApiResponse(code = 201, message = "resource created successfully"),
            @ApiResponse(code = 409, message = "resource already exists in the database", response = Error.class)})
    @PostMapping
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest account) {
        registerService.register(account);
        LOGGER.debug("método register executado: " + account.toString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "${RegisterController.confirm.value}", notes = "${RegisterController.confirm.notes}", nickname = "${RegisterController.confirm.nickname}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "request accepted successfully"),
            @ApiResponse(code = 400, message = "client bad request", response = Error.class),
            @ApiResponse(code = 404, message = "resource not found", response = Error.class)})
    @GetMapping("/confirm")
    public ResponseEntity<Void> confirmToken(@RequestParam("token") String token) {
        registerService.confirmToken(token);
        LOGGER.debug("método confirmToken executado: " + token);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
