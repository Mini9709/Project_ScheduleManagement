package com.example.schedulemanagementapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> responseExHandler(ResponseStatusException e) {

        return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValidExHandler(MethodArgumentNotValidException e) {

        return new ResponseEntity<>(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ResponseEntity<?> exHandler(Exception e) {

        return new ResponseEntity<>("유효하지 않는 값을 입력하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
