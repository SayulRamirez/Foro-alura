package com.challeng.foro.exceptions;

public class NotFoundAnswer extends RuntimeException {
    public NotFoundAnswer(String message) {super(message);}

    @Override
    public String getMessage() {return super.getMessage();}
}
