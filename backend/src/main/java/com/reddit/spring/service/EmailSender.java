package com.reddit.spring.service;

public interface EmailSender {
    void send(String to, String email);
}
