package com.scaler.todolist.DTO;

import com.scaler.todolist.models.Task;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponseDTO extends BaseResponseDTO{
    private Task task;
}
