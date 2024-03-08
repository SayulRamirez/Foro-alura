package com.challeng.foro.controllers;

import com.challeng.foro.exceptions.BadParameterRequestException;
import com.challeng.foro.exceptions.NotFoundAnswer;
import com.challeng.foro.exceptions.NotFoundTopic;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@RestControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {


    @ExceptionHandler(BadParameterRequestException.class)
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp: ", LocalDateTime.now());
        body.put("message: ", ex.getMessage());
        body.put("Error: ", HttpStatus.BAD_REQUEST.toString());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings("all")
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, Object> errors = new HashMap<>();

        errors.put("timestamp: ", LocalDateTime.now());

        ex.getBindingResult().getAllErrors().forEach(
                (error) -> {

                    String fieldName = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();

                    errors.put(fieldName, message);
                }
        );

        errors.put("Error: ", status.toString());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundTopic.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected Map<String, Object> handleNotFoundTopic(RuntimeException e) {

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("TimeStamp", LocalDateTime.now());
        response.put("Message", e.getMessage());
        response.put("Error", HttpStatus.NOT_FOUND);

        return response;
    }

    @ExceptionHandler(NotFoundAnswer.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected  Map<String, Object> handleNotFoundAnswer(RuntimeException e) {

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("TimeStamp", LocalDateTime.now());
        response.put("Message", e.getMessage());
        response.put("Error:", HttpStatus.NOT_FOUND);

        return response;
    }
}
