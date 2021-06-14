package com.reddit.spring.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // check if request contains the Authorization header
        String header = request.getHeader("Authorization");
        if (Strings.isNullOrEmpty(header) || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
        } else {
            String token = header.replace("Bearer ", "");
            try {
                Jws<Claims> jws = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor("thisIsMySecurityKeyForAShortTimeAndIllChangeThisLittleShit0321321309123".getBytes()))
                        .build()
                        .parseClaimsJws(token);
                Claims body = jws.getBody();

                // grab the username and authorities from token
                String username = body.getSubject();
                var authorities = (List<Map<String, String>>) body.get("authorities");

                // map authorities to SimpleGranted
                Set<SimpleGrantedAuthority> authority = authorities
                        .stream().map(x -> new SimpleGrantedAuthority(x.get("authority"))).collect(Collectors.toSet());

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        authority
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response); // sempre passar o filtro pra frente (não fique mais de meia hora procurando o erro de novo)
            } catch (JwtException e) {
                throw new IllegalStateException(String.format("jwt %s cannot be trusted", token));
            }
        }
    }
}
