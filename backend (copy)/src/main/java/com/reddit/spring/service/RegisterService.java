package com.reddit.spring.service;

import com.reddit.spring.dto.RegisterRequestt;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    public String register(RegisterRequestt request) {
        return "works";
    }
}
