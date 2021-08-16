package com.reddit.spring.filter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.reddit.spring.dto.Error.createResponse;
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;

public class CustomForbiddenEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        createResponse(res, SC_FORBIDDEN, e.getMessage());
    }
}
