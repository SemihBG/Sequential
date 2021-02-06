package com.semih.SequentialWebService.controller.advice;

import com.semih.SequentialWebService.exception.DuplicatedListNameException;
import com.semih.SequentialWebService.exception.NonexistentTableException;
import com.semih.SequentialWebService.exception.WrongAuthenticationCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({NonexistentTableException.class,DuplicatedListNameException.class, WrongAuthenticationCodeException.class})
    public ResponseEntity<String> nonexistentTableExceptionHandler(Exception exception){
        HttpStatus httpStatus=HttpStatus.BAD_REQUEST;
        String response="<h1>Exception</h1>Exception Message = "+exception.getMessage();
        return new ResponseEntity<String>(response,httpStatus);
    }

}
