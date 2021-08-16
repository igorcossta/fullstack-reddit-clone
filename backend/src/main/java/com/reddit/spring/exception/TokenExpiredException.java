package com.reddit.spring.exception;

import com.reddit.spring.exception.types.BadRequestException;

public class TokenExpiredException extends BadRequestException {

    public TokenExpiredException(String message) {
        super(message);
    }

    public TokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
