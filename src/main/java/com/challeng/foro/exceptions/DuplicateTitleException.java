package com.challeng.foro.exceptions;

public class DuplicateTitleException extends RuntimeException {

    public DuplicateTitleException(String message){super(message);}

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
