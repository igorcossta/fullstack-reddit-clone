package com.reddit.spring.controller;

import com.reddit.spring.dto.RegisterRequestt;
import com.reddit.spring.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
@AllArgsConstructor
public class RegisterController {
    private final RegisterService registerService;

    public String register(@RequestBody RegisterRequestt request) {
        return registerService.register(request);
    }
}
