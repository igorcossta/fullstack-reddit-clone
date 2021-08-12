package com.reddit.spring.exception;

public class EmailExistsException extends RedditException {

    public EmailExistsException(String message) {
        super(message);
    }

    public EmailExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
