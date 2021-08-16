package com.reddit.spring.exception;

import com.reddit.spring.exception.types.BadRequestException;

public class SubredditExistsException extends BadRequestException {

    public SubredditExistsException(String message) {
        super(message);
    }

    public SubredditExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
