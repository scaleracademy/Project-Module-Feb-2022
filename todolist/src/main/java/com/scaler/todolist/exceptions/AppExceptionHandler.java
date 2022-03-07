package com.scaler.todolist.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value={TaskServiceException.class})
    public ResponseEntity<Object> handleTaskServiceException(TaskServiceException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        ResponseEntity<Object> objectResponseEntity = new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);

        return  objectResponseEntity;
    }


}
