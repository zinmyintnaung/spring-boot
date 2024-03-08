package com.springboot.restapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {
//add an exception handler using @ExceptionHandler annotation
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
        //create StudentErrorResponse 
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        //return error response
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    //add another exception handler to handle generic error
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
        //create StudentErrorResponse 
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("Provide student Id only. Bad Request.");
        error.setTimeStamp(System.currentTimeMillis());

        //return error response
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    } 
}
