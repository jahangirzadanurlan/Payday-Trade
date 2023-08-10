package com.example.demo1.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum Exceptions {
    STOCK_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,"Stock not found"),
    STOCK_LIST_IS_EMPTY_EXCEPTION(HttpStatus.NOT_FOUND,"Stock list is empty");

    private final HttpStatus httpStatus;
    private final String message;
}
