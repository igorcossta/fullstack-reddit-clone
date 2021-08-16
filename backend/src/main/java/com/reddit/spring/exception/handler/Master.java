package com.reddit.spring.exception.handler;

import com.reddit.spring.exception.SubredditExistsException;
import com.reddit.spring.exception.SubredditNotFoundException;
import com.reddit.spring.exception.ValidationException;
import com.reddit.spring.exception.types.BadRequestException;
import com.reddit.spring.exception.types.ForbiddenException;
import com.reddit.spring.exception.types.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.reddit.spring.dto.Error.createResponse;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j
public class Master extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ValidationException.class, SubredditExistsException.class, SubredditNotFoundException.class})
    public ResponseEntity<Object> handle(Exception ex) {
        log.info("throwing exception: {} | message -> {}", ex.getClass().getSimpleName(), ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        if (ex instanceof BadRequestException) {
            status = BAD_REQUEST;
            return exceptionResponse(ex, headers, status);
        } else if (ex instanceof NotFoundException) {
            status = NOT_FOUND;
            return exceptionResponse(ex, headers, status);
        } else if (ex instanceof ForbiddenException) {
            status = FORBIDDEN;
            return exceptionResponse(ex, headers, status);
        } else {
            status = INTERNAL_SERVER_ERROR;
            return exceptionResponse(ex, headers, status);
        }
    }

    private ResponseEntity<Object> exceptionResponse(Exception ex, HttpHeaders headers, HttpStatus status) {
        var body = createResponse(ex, status);
        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest req) {
        var body = createResponse(ex, status);
        return super.handleExceptionInternal(ex, body, headers, status, req);
    }
}
