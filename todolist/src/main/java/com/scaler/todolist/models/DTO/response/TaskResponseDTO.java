package com.scaler.todolist.models.DTO.response;

import com.scaler.todolist.models.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class TaskResponseDTO {
    private int id;
    private String name;
    private LocalDate due;
    private Boolean done;

    public TaskResponseDTO(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.due = task.getDue();
        this.done = task.getDone();
    }
}
