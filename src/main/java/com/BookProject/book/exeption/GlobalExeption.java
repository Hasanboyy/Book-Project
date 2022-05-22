package com.BookProject.book.exeption;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExeption {
    @ExceptionHandler
    public ResponseEntity<?> exception(BookExeption b){
        return ResponseEntity.badRequest().body(b.getMessage());
    }
}
