package com.challeng.foro.controllers;

import com.challeng.foro.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class RestResponseEntityEcxeptionHandler
        extends ResponseEntityExceptionHandler {


    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("timestamp: ", LocalDateTime.now());
        body.put("message: ", ex.getMessage());
        body.put("Error: ", HttpStatus.BAD_REQUEST.toString());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> errorValidation(MethodArgumentNotValidException e) {
        List<ValidationErrorData> errors = e.getFieldErrors().stream().map(ValidationErrorData::new).toList();

        return ResponseEntity.badRequest().body(errors);
    }

    private record ValidationErrorData(String field, String error) {
        public ValidationErrorData(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }*/
}
