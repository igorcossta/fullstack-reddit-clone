package com.reddit.spring.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.reddit.spring.dto.LoginResponse;
import com.reddit.spring.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@AllArgsConstructor
public class Jwt {
    private final static String security = "secret";
    private final static Algorithm algorithm = Algorithm.HMAC256(security.getBytes(UTF_8));

    public static String createToken(User user, String issuer) {
        String token = JWT.create()
                .withIssuer(issuer)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60))
                .withSubject(user.getUsername())
                .withClaim("authority", user.getAuthorities()
                        .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        return token;
    }

    public static String createRefreshToken(String username, String issuer) {
        String token = JWT.create()
                .withIssuer(issuer)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 120))
                .withSubject(username)
                .sign(algorithm);
        return token;
    }

    public static LoginResponse createTokenResponse(String token, String refreshToken, User user) {
        return new LoginResponse(
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                !user.isAccountNonLocked(),
                user.isEnabled(),
                token,
                refreshToken
        );
    }

    public static UsernamePasswordAuthenticationToken createCredentialsFromToken(String token) {
        DecodedJWT decodedJWT = verifyToken(token);
        // grab the username from token
        String username = decodedJWT.getSubject();
        // grab an string list with authorities
        String[] authorities = decodedJWT.getClaim("authority").asArray(String.class);
        // map authorities from string list to Set of Simple Granted Authority
        Set<GrantedAuthority> authority = new HashSet<>();
        Arrays.stream(authorities).forEach(i -> authority.add(new SimpleGrantedAuthority(i)));
        return new UsernamePasswordAuthenticationToken(username, null, authority);
    }

    private static DecodedJWT verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        } catch (JWTVerificationException ex) {
            log.error("verify token throws an exception -> {}", ex.getMessage());
            throw new JWTVerificationException(ex.getMessage());
        }
    }
}
