package com.reddit.spring.exception;

import com.reddit.spring.dto.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleInternalServerError(Exception exception) {
        Error error = buildErrorResponse(exception, INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(error, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException exception) {
        HttpStatus status = httpStatusFor(exception);
        Error error = buildErrorResponse(exception, status);
        return new ResponseEntity<>(error, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error body = buildErrorResponse(exception, status);
        return handleExceptionInternal(exception, body, headers, status, request);
    }

    @ExceptionHandler({TokenNotFoundException.class, TokenConfirmedException.class, TokenExpiredException.class})
    public ResponseEntity<Object> handleTokenException(Exception exception) {
        HttpStatus status = httpStatusFor(exception);
        Error error = buildErrorResponse(exception, status);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler({EmailExistsException.class, InvalidEmailException.class})
    public ResponseEntity<Object> handleEmailException(Exception exception) {
        HttpStatus status = httpStatusFor(exception);
        Error error = buildErrorResponse(exception, status);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler({PostNotFoundException.class})
    public ResponseEntity<Object> handlePostException(Exception exception) {
        HttpStatus status = httpStatusFor(exception);
        Error error = buildErrorResponse(exception, status);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler({SubredditNotFoundException.class})
    public ResponseEntity<Object> handleSubredditException(Exception exception) {
        HttpStatus status = httpStatusFor(exception);
        Error error = buildErrorResponse(exception, status);
        return new ResponseEntity<>(error, status);
    }

    /* helper functions */

    private Error buildErrorResponse(Exception exception, HttpStatus status) {
        LOGGER.debug("Throwing exception " + exception.getClass().getSimpleName());
        return new Error(now(), status.value(), status.getReasonPhrase(), messageFor(exception));
    }

    private String messageFor(Exception e) {
        if (e instanceof MethodArgumentNotValidException) return "some fields are incorrect";
        else return e.getLocalizedMessage();
    }

    private HttpStatus httpStatusFor(Exception e) {
        if (e instanceof TokenNotFoundException || e instanceof PostNotFoundException || e instanceof SubredditNotFoundException)
            return NOT_FOUND;
        else if (e instanceof TokenConfirmedException || e instanceof TokenExpiredException || e instanceof InvalidEmailException || e instanceof BadCredentialsException)
            return BAD_REQUEST;
        else if (e instanceof EmailExistsException) return CONFLICT;
        else return INTERNAL_SERVER_ERROR;
    }
}
