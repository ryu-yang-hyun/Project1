package com.example.springboot.exception;

public class ConversionException extends RuntimeException {
    private static final long serialVersionUID = 7026102025430486487L;

    public ConversionException(final String message, final Throwable t) {
        super(message, t);
    }

    public ConversionException(final String message) {
        super(message);
    }

    public ConversionException(Throwable t) {
        super(t);
    }
}
