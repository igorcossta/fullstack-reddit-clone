package com.reddit.spring.exception;

import com.reddit.spring.exception.types.BadRequestException;

public class TokenConfirmedException extends BadRequestException {

    public TokenConfirmedException(String message) {
        super(message);
    }

    public TokenConfirmedException(String message, Throwable cause) {
        super(message, cause);
    }
}
