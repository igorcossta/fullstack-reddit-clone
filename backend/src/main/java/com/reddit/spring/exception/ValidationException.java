package com.reddit.spring.exception;

import com.reddit.spring.exception.types.BadRequestException;

public class ValidationException extends BadRequestException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
