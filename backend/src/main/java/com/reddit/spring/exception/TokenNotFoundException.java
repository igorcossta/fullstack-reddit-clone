package com.reddit.spring.exception;

import com.reddit.spring.exception.types.NotFoundException;

public class TokenNotFoundException extends NotFoundException {

    public TokenNotFoundException(String message) {
        super(message);
    }

    public TokenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
