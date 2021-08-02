package com.reddit.spring.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reddit.spring.dto.Error;
import com.reddit.spring.dto.UsernameAndPasswordRequest;
import com.reddit.spring.exception.RedditException;
import com.reddit.spring.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.reddit.spring.jwt.Jwt.createToken;
import static com.reddit.spring.jwt.Jwt.createTokenResponse;
import static java.time.ZonedDateTime.now;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager manager;

    public CustomAuthenticationFilter(AuthenticationManager manager) {
        this.manager = manager;
    }

    /*
        recebe um usuario e senha da requisicao e envia para o authentication manager
     */

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            UsernameAndPasswordRequest credentials = new ObjectMapper().
                    readValue(req.getInputStream(), UsernameAndPasswordRequest.class);
            // verificar os dados enviados do cliente

            // ...
            String username = credentials.getUsername();
            String password = credentials.getPassword();
            log.info("{} trying to log in with password {}", username, password);

            UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, password);

            return manager.authenticate(user);
        } catch (IOException e) {
            throw new RedditException(e.getMessage());
        }
    }

    /*
        se as credenciais existirem no banco de dados o metodo sera executado
     */

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {
        log.info("successful Authentication");

        User user = (User) auth.getPrincipal();
        String issuer = req.getRequestURI();
        String token = createToken(user, issuer);

        res.setContentType(APPLICATION_JSON_VALUE);
        res.setHeader("Authorization", token);
        new ObjectMapper().writeValue(res.getOutputStream(), createTokenResponse(user));
    }

    /*
        se as credenciais nao existirem no banco de dados o metodo sera executado
     */

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException failed) throws IOException {
        log.info("unsuccessful Authentication");
        res.setStatus(SC_UNAUTHORIZED);
        res.setContentType(APPLICATION_JSON_VALUE);
        var error = new Error(now(), SC_UNAUTHORIZED, "Unauthorized", "Maybe your credentials are invalid", req.getServletPath());
        new ObjectMapper().writeValue(res.getOutputStream(), error);
    }
}
