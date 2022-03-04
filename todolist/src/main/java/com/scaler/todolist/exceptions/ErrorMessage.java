package com.scaler.todolist.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class ErrorMessage {
    private Date timestamp;
    private String message;

}
