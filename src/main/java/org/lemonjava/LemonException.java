package org.lemonjava;

public class LemonException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LemonException(final String message) {
        super(message);
    }

    public LemonException(final String message, final Throwable cause) {
        super(message, cause);
    }
}