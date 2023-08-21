package com.test.socialmedia.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super();
    }

    public NotFoundException(String s) {
        super(s);
    }

    public NotFoundException(String s, Object... args) {
        super(String.format(s, args));
    }
}
