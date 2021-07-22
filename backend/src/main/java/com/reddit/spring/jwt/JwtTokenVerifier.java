package com.reddit.spring.jwt;

import com.google.common.base.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class JwtTokenVerifier extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        // check if request contains the Authorization header
        String header = request.getHeader("Authorization");
        if (Strings.isNullOrEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        // grab the token from header
        String token = header.replace("Bearer ", "");

        // grab the username from token
        String username = JwtUtils.extractUsername(token);

        // grab the authorities from token
        Set<SimpleGrantedAuthority> authority = JwtUtils.toGrantedAuthority(token);

        // create an user
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                username,
                null,
                authority
        );

        // access granted and so on
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
