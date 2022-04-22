package com.scaler.taskmanager.tasks;

import com.scaler.taskmanager.ResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponseDTO extends ResponseDTO {
    private TaskEntity task;
}
