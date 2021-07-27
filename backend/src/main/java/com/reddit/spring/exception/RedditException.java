package com.reddit.spring.exception;

public class RedditException extends RuntimeException {

    public RedditException(String message) {
        super(message);
    }

    public RedditException(String message, Throwable cause) {
        super(message, cause);
    }
}
