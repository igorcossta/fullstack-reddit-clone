//package com.reddit.spring.security;
//
//import com.google.common.base.Strings;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Slf4j
//public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
//
//    public TokenAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher,
//                                     AuthenticationManager authenticationManager) {
//        super(requiresAuthenticationRequestMatcher, authenticationManager);
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest req,
//                                                HttpServletResponse res) throws AuthenticationException, IOException, ServletException {
//        String header = req.getHeader("Authorization");
//        if (Strings.isNullOrEmpty(header) || !header.startsWith("Bearer ")) {
//            throw new BadCredentialsException("missing authentication token");
//        } else {
//            String token = header.replace("Bearer ", "");
//            final Authentication auth = new UsernamePasswordAuthenticationToken("pro@email.com", token);
//            log.info("token ->" + token + " auth -> " + auth.getName());
//            return getAuthenticationManager().authenticate(auth);
//        }
//    }
//}
