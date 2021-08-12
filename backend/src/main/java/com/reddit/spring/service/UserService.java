package com.reddit.spring.service;

import com.reddit.spring.exception.EmailExistsException;
import com.reddit.spring.model.User;
import com.reddit.spring.model.VerificationToken;
import com.reddit.spring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final VerificationTokenService verificationTokenService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("username cannot be found"));
    }

    public String signUpUser(User user) {
        boolean userExists = userRepository.findByUsername(user.getUsername()).isPresent();

        if (userExists) {
            throw new EmailExistsException("the email already taken");
        }

        String encode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        userRepository.save(user);

        // save a verification token for new user
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        verificationTokenService.saveVerificationToken(verificationToken);
        return token;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username cannot be found"));
    }
}
