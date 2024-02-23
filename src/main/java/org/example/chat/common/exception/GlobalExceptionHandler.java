package org.example.chat.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessExceptionHandler.class)
    public ResponseEntity<ExceptionDto.Response> handleCustomException(BusinessExceptionHandler ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionDto.Response.builder().message(String.format("[%s] %s", ex.getErrorCode().getDivisionCode(), ex.getMessage())).build());
    }
}
