package com.reddit.spring.exception;

import com.reddit.spring.exception.types.BadRequestException;

public class PostExistsException extends BadRequestException {

    public PostExistsException(String message) {
        super(message);
    }

    public PostExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
