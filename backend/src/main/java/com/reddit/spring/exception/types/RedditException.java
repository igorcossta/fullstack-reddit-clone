package com.reddit.spring.exception.types;

public class RedditException extends RuntimeException {

    public RedditException(String message) {
        super(message);
    }

    public RedditException(String message, Throwable cause) {
        super(message, cause);
    }
}
