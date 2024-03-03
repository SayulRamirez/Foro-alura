package com.challeng.foro.exceptions;

public class BadParameterRequestException extends RuntimeException {

    public BadParameterRequestException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
