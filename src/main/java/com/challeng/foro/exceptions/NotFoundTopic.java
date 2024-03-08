package com.challeng.foro.exceptions;

public class NotFoundTopic extends RuntimeException {

    public NotFoundTopic(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
