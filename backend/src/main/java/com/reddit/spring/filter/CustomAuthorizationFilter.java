package com.reddit.spring.filter;

import com.google.common.base.Strings;
import com.reddit.spring.jwt.Jwt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        if (req.getServletPath().equals("/api/signin")) chain.doFilter(req, res);
        else {
            String header = req.getHeader(AUTHORIZATION);
            Cookie[] cookie = req.getCookies();
            if (Strings.isNullOrEmpty(header) || !header.startsWith("Bearer ")) chain.doFilter(req, res);
            else {
                String token = header.replace("Bearer ", "");
                try {
                    UsernamePasswordAuthenticationToken credentials = Jwt.createCredentialsFromToken(token);
                    SecurityContextHolder.getContext().setAuthentication(credentials);
                    chain.doFilter(req, res);
                } catch (Exception ex) {
                    chain.doFilter(req, res);
                }
            }
        }
    }
}
