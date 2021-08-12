package com.reddit.spring.exception;

public class TokenConfirmedException extends TokenException {

    public TokenConfirmedException(String message) {
        super(message);
    }

    public TokenConfirmedException(String message, Throwable cause) {
        super(message, cause);
    }
}
