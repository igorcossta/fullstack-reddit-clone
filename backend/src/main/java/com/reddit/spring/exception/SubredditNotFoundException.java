package com.reddit.spring.exception;

public class SubredditNotFoundException extends RuntimeException {
    public SubredditNotFoundException(String message) {
        super(message);
    }

    public SubredditNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubredditNotFoundException(Throwable cause) {
        super(cause);
    }
}
