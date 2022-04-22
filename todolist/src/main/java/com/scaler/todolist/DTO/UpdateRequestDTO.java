package com.scaler.todolist.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateRequestDTO {
    private LocalDate due;
    private Boolean done;
}
