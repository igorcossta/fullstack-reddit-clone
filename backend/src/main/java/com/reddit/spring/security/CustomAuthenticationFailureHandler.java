//package com.reddit.spring.security;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
////    private ObjectMapper objectMapper = new ObjectMapper();
//    private static Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
////        response.setStatus(HttpStatus.UNAUTHORIZED.value());
////        Map<String, Object> data = new HashMap<>();
////        data.put("timestamp", Calendar.getInstance().getTime());
////        data.put("exception", e.getMessage());
////
////        response.getOutputStream().println(objectMapper.writeValueAsString(data));
//        throw new IllegalStateException(e.getMessage());
//    }
//}

//    @Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        return new CustomAuthenticationFailureHandler();
//    }
