package com.reddit.spring.controller;

import com.reddit.spring.dto.RegisterRequest;
import com.reddit.spring.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
@AllArgsConstructor
public class RegisterController {
    private final RegisterService registerService;

    @PostMapping
    public String register(@RequestBody RegisterRequest request) {
        return registerService.register(request);
    }

    @GetMapping("/confirm")
    public String confirmToken(@RequestParam("token") String token) {
        return registerService.confirmToken(token);
    }
}
