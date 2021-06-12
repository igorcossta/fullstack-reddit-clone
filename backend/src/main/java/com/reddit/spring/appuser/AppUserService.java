package com.reddit.spring.appuser;

import com.google.common.collect.Sets;
import com.reddit.spring.exception.SpringRedditException;
import com.reddit.spring.model.VerificationToken;
import com.reddit.spring.service.VerificationTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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
@Slf4j
public class AppUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND = "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final VerificationTokenService verificationTokenService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByEmail(s)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, s)));
        User u = new User(
                user.getEmail(), user.getPassword(),
                user.getEnabled(), true, true, !user.getLocked(),
                Sets.newHashSet(new SimpleGrantedAuthority(user.getRole().name()))
        );
        System.out.println(u.getUsername() + " " + u.getAuthorities() + " " + u.isEnabled() + " " + u.isAccountNonLocked());
        return u;
    }

    public String signUpUser(AppUser user) {
        boolean userExists = appUserRepository.findByEmail(user.getEmail())
                .isPresent();
        if (userExists) {
            throw new IllegalStateException("email already taken");
        }
        String encode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        appUserRepository.save(user);

        // save a verification token for new user
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        verificationTokenService.saveVerificationToken(verificationToken);
        // send an email for new user to activate the token
        return token;
    }

    public int enableUser(String email) {
        return appUserRepository.enableUser(email);
    }

    public AppUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return appUserRepository.findByEmail(username)
                .orElseThrow(() -> new SpringRedditException("user not found"));
    }
}
