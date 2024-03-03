package com.challeng.foro.exceptions;

public class NotExistsCourseException extends RuntimeException {

    public NotExistsCourseException(String message){super(message);}

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
