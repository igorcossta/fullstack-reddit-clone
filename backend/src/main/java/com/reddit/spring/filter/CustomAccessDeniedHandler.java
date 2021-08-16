package com.reddit.spring.filter;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.reddit.spring.dto.Error.createResponse;
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException e) throws IOException {
        createResponse(res, SC_FORBIDDEN, "You do not have permission to access this resource");
    }
}
