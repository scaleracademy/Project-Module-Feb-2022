package com.scaler.taskmanager.tasks;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateTaskRequestBody {
    private LocalDate dueDate;
    private Boolean status;
}
