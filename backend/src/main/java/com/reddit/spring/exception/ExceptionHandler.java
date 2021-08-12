package com.reddit.spring.exception;

import com.reddit.spring.dto.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static com.reddit.spring.dto.Error.Field;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler({TokenNotFoundException.class, TokenConfirmedException.class, TokenExpiredException.class, PasswordException.class, EmailExistsException.class})
    public ResponseEntity<Object> handleMyException(Exception ex, WebRequest req) throws Exception {
        LOGGER.debug("Throwing exception " + ex.getClass().getSimpleName() + " | message -> " + ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        if (ex instanceof TokenNotFoundException) {
            status = NOT_FOUND;
            return this.handleMyExceptionInternal(ex, headers, status, req);
        } else if (ex instanceof TokenExpiredException || ex instanceof TokenConfirmedException) {
            status = BAD_REQUEST;
            return this.handleMyExceptionInternal(ex, headers, status, req);
        } else if (ex instanceof PasswordException) {
            status = BAD_REQUEST;
            return this.handleMyExceptionInternal(ex, headers, status, req);
        } else if (ex instanceof EmailExistsException) {
            status = UNPROCESSABLE_ENTITY;
            return this.handleMyExceptionInternal(ex, headers, status, req);
        }
        throw ex;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var body = createBody(exception, status, request);
        return this.handleExceptionInternal(exception, body, headers, status, request);
    }

    private ResponseEntity<Object> handleMyExceptionInternal(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest req) {
        var body = createBody(ex, status, req);
        return new ResponseEntity<>(body, headers, status);
    }

    // helper functions //

    private Error createBody(Exception ex, HttpStatus status, WebRequest req) {
        if (ex instanceof MethodArgumentNotValidException)
            return new Error(status.value(), status.getReasonPhrase(), "some fields are incorrect", req.getContextPath(), getBindingResult((MethodArgumentNotValidException) ex));
        return new Error(status.value(), status.getReasonPhrase(), ex.getMessage(), req.getContextPath());
    }

    private List<Field> getBindingResult(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream().map(e -> new Field(e.getField(), e.getDefaultMessage())).collect(toList());
    }
}
