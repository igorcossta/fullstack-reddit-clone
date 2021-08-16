package com.reddit.spring.exception;

import com.reddit.spring.exception.types.BadRequestException;

public class EmailExistsException extends BadRequestException {

    public EmailExistsException(String message) {
        super(message);
    }

    public EmailExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
