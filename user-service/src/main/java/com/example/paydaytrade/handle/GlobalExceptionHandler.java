package com.example.paydaytrade.handle;

import com.example.paydaytrade.exception.ApplicationException;
import com.example.paydaytrade.model.dto.response.ExceptionResponse;
import com.example.paydaytrade.model.enums.Exceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ExceptionResponse> handle(ApplicationException applicationException){
        Exceptions exception = applicationException.getException();

        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(ExceptionResponse.builder()
                        .message(exception.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
