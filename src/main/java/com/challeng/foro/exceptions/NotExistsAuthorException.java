package com.challeng.foro.exceptions;

public class NotExistsAuthorException extends RuntimeException {

    public NotExistsAuthorException(String message){super(message);}

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
