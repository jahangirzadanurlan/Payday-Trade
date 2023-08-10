package com.example.demo1.exception;

import com.example.demo1.model.enums.Exceptions;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException{
    private final Exceptions exception;

    public ApplicationException(Exceptions exception){
        super(exception.getMessage());
        this.exception=exception;
    }

}
