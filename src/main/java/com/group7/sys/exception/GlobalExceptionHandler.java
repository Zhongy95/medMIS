package com.group7.sys.exception;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = medMISException.class)
    @ResponseBody
    public ResponseEntity<?> handler(medMISException e, WebRequest request) {
        return new ResponseEntity<>(new ExceptionDetails(e), e.getHttpStatus());
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class, NoHandlerFoundException.class})
    public ResponseEntity<?> resourceNotFoundExceptionHandler(Exception e, WebRequest request) {
        return handler(new medMISException(e.getMessage(), HttpStatus.NOT_FOUND), request);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> accessDeniedExceptionHandler(AuthenticationException e, WebRequest request) {
        return handler(new medMISException(e.getMessage(), HttpStatus.FORBIDDEN), request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e, WebRequest request) {
        return handler(new medMISException(e.getMessage(), HttpStatus.BAD_REQUEST), request);
    }
}