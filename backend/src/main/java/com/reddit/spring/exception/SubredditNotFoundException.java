package com.reddit.spring.exception;

import com.reddit.spring.exception.types.NotFoundException;

public class SubredditNotFoundException extends NotFoundException {

    public SubredditNotFoundException(String message) {
        super(message);
    }

    public SubredditNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
