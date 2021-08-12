package com.reddit.spring.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reddit.spring.dto.Error;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException e) throws IOException {
        res.setStatus(SC_FORBIDDEN);
        res.setContentType(APPLICATION_JSON_VALUE);
        var error = new Error(SC_FORBIDDEN, "Forbidden", "You do not have permission to access this resource", req.getServletPath());
        new ObjectMapper().writeValue(res.getOutputStream(), error);
    }
}
