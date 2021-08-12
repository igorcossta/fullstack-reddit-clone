package com.reddit.spring.exception;

public class PasswordException extends RedditException {

    public PasswordException(String s) {
        super(s);
    }

    public PasswordException(String s, Exception e) {
        super(s, e);
    }
}
