package com.scaler.todolist.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    INVALID_TASK_ID("Invalid task Id");


    private String errorMessage;

    ErrorMessages(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
