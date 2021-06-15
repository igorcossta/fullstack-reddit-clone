package com.reddit.spring.exception;

public class TokenConfirmedException extends RuntimeException {
    public TokenConfirmedException(String message) {
        super(message);
    }

    public TokenConfirmedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenConfirmedException(Throwable cause) {
        super(cause);
    }
}
