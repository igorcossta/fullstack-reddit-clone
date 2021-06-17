package com.reddit.spring.service;

import com.google.common.collect.Sets;
import com.reddit.spring.exception.EmailExistsException;
import com.reddit.spring.model.AppUser;
import com.reddit.spring.model.VerificationToken;
import com.reddit.spring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
        AppUser user = userRepository.findByEmail(s).orElseThrow(() -> new UsernameNotFoundException("username cannot be found"));
        User u = new User(
                user.getEmail(), user.getPassword(),
                user.getEnabled(), true, true, !user.getLocked(),
                Sets.newHashSet(new SimpleGrantedAuthority(user.getRole().name()))
        );
        return u;
    }

    public String signUpUser(AppUser user) {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if (userExists) {
            throw new EmailExistsException("email already taken");
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

    public AppUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("username cannot be found"));
    }
}
