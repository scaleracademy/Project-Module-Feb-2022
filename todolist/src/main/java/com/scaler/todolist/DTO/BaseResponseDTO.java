package com.scaler.todolist.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponseDTO {
    private int status;
    private String message;
}
