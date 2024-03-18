package com.challeng.foro.exceptions;

public class NotFoundUser extends RuntimeException {

    public NotFoundUser(String message){super(message);}

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
