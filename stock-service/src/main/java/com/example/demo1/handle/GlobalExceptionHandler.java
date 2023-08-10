package com.example.demo1.handle;

import com.example.demo1.exception.ApplicationException;
import com.example.demo1.model.dto.response.ExceptionResponse;
import com.example.demo1.model.enums.Exceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionResponse> globalExceptionHandler(ApplicationException applicationException){
        Exceptions exceptions = applicationException.getException();

        return ResponseEntity
                .status(exceptions.getHttpStatus())
                .body(ExceptionResponse.builder()
                        .message(exceptions.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

}
