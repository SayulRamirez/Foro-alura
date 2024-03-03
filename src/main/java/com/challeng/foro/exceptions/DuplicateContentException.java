package com.challeng.foro.exceptions;

public class DuplicateContentException extends RuntimeException {

    public DuplicateContentException(String message){super(message);}

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
