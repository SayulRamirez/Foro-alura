package com.challeng.foro.controllers;

import com.challeng.foro.exceptions.BadParameterRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class RestResponseEntityEcxeptionHandler
        extends ResponseEntityExceptionHandler {


    @ExceptionHandler(BadParameterRequestException.class)
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp: ", LocalDateTime.now());
        body.put("message: ", ex.getMessage());
        body.put("Error: ", HttpStatus.BAD_REQUEST.toString());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

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

}
