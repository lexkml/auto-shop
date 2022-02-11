package com.kamelchukov.autoshop.handler;

import com.kamelchukov.autoshop.exception.EntityNotFoundException;
import com.kamelchukov.autoshop.exception.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponse> handleException(EntityNotFoundException ex, ServletWebRequest request) {

        return returnExceptionResponse(ex, request);
    }

    private ResponseEntity<ExceptionResponse> returnExceptionResponse(
            EntityNotFoundException ex, ServletWebRequest request) {
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage(), request.getRequest().getRequestURI()), HttpStatus.NOT_FOUND);
    }

}
