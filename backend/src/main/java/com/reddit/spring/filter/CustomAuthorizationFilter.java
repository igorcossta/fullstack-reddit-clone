package com.reddit.spring.filter;

import com.google.common.base.Strings;
import com.reddit.spring.jwt.Jwt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
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
            // ways to perform an appropriate response
            // 1 option - let responsibility to spring (default)
            if (Strings.isNullOrEmpty(header) || !header.startsWith("Bearer ")) chain.doFilter(req, res);
                // 2 option - throws an exception and catch on @ControllerAdvice (modify the response)
//            if (Strings.isNullOrEmpty(header) || !header.startsWith("Bearer ")) throw new RedditException("You must be authenticated to access this resource");
            else {
                String token = header.replace("Bearer ", "");
                try {
                    UsernamePasswordAuthenticationToken credentials = Jwt.createCredentialsFromToken(token);
                    SecurityContextHolder.getContext().setAuthentication(credentials);
                    chain.doFilter(req, res);
                } catch (Exception ex) {
                    // same options as above
                    chain.doFilter(req, res);
                }
            }
        }
    }
}
