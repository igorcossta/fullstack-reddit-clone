package com.reddit.spring.service;

import com.reddit.spring.model.VerificationToken;
import com.reddit.spring.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VerificationTokenService {
    private final VerificationTokenRepository verificationTokenRepository;

    public void saveVerificationToken(VerificationToken token) {
        verificationTokenRepository.save(token);
    }

    public Optional<VerificationToken> getVerificationToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return verificationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}
