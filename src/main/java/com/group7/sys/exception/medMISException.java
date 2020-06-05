package com.group7.sys.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import java.util.Date;

@Data
public class medMISException extends Exception {
    private String status;
    private Date time;
    private HttpStatus httpStatus;

    public medMISException(String message, HttpStatus httpStatus) {
        super(message);
        this.status = httpStatus.getReasonPhrase().toUpperCase().replace(' ', '_');
        this.httpStatus = httpStatus;
        this.time = new Date();
    }

    public medMISException(String message, String status, HttpStatus httpStatus) {
        super(message);
        this.status = status;
        this.time = new Date();
        this.httpStatus = httpStatus;
    }
}
