package com.group7.sys.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = medMISException.class)
    @ResponseBody
    public ResponseEntity<?> handler(medMISException e){
        return new ResponseEntity<>(new ExceptionDetails(e), e.getHttpStatus());
    }
}