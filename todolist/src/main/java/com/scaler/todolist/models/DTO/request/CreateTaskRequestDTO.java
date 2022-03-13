package com.scaler.todolist.models.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CreateTaskRequestDTO {
    private String name;
    private LocalDate due;
    private Boolean done;
}
