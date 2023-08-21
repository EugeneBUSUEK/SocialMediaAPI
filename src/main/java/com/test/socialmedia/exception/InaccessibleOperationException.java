package com.test.socialmedia.exception;

public class InaccessibleOperationException extends RuntimeException {
    public InaccessibleOperationException() {
        super();
    }

    public InaccessibleOperationException(String s) {
        super(s);
    }

    public InaccessibleOperationException(String s, Object... args) {
        super(String.format(s, args));
    }
}
