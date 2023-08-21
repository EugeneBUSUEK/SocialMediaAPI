package com.test.socialmedia.exception;

public class InvalidDirectionFormat extends RuntimeException {
    public InvalidDirectionFormat() {
        super();
    }

    public InvalidDirectionFormat(String s) {
        super(s);
    }

    public InvalidDirectionFormat(String s, Object... args) {
        super(String.format(s, args));
    }
}
