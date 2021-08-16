package com.reddit.spring.exception;

import com.reddit.spring.exception.types.NotFoundException;

public class PostNotFoundException extends NotFoundException {

    public PostNotFoundException(String message) {
        super(message);
    }

    public PostNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
