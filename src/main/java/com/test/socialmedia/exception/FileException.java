package com.test.socialmedia.exception;

public class FileException extends RuntimeException {
    public FileException() {
        super();
    }

    public FileException(String s) {
        super(s);
    }

    public FileException(String s, Object... args) {
        super(String.format(s, args));
    }
}
