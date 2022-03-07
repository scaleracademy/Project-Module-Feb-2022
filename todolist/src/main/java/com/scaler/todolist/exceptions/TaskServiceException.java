package com.scaler.todolist.exceptions;

public class TaskServiceException extends RuntimeException{
    public TaskServiceException(String message) {
        super(message);
    }
}
