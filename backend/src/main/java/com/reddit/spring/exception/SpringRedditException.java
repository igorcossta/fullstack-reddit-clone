package com.reddit.spring.exception;

public class SpringRedditException extends RuntimeException {

    public SpringRedditException(String s) {
        super(s);
    }
    public SpringRedditException(String s, Exception e) {
        super(s, e);
    }
}
