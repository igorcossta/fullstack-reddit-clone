package com.reddit.spring.controller;

import com.reddit.spring.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
@ApiIgnore
public class TestController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<String> test(@PathVariable long id) {
        System.out.println("SEARCHING FOR ID " + id);
        return new ResponseEntity<>("api/test access granted", HttpStatus.OK);
    }

    @GetMapping("/principal")
    public ResponseEntity<String> test2() {
        System.out.println("GETTING PRINCIPAL");
        return new ResponseEntity<>("api/principal access granted " + userService.getCurrentUser().getUsername(), HttpStatus.OK);
    }
}
