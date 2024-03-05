package com.challeng.foro.exceptions;

import com.challeng.foro.domain.ResponseTopic;
import org.springframework.http.ResponseEntity;

public class NotFoundTopic extends RuntimeException {

    public NotFoundTopic(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
