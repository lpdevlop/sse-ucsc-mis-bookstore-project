package com.ucsc.bookstoreproject.exceptions;
import org.springframework.http.HttpStatus;


public class CustomException extends RuntimeException{

    private final HttpStatus httpStatus;

    public CustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }


    public CustomException(HttpStatus httpStatus,String message, Throwable cause) {
        super(message, cause);
        this.httpStatus=httpStatus;
    }
}
