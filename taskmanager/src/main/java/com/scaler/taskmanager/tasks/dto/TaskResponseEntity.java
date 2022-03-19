package com.scaler.taskmanager.tasks;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Getter
@Setter
public class TaskResponseEntity {
    private Long id;
    private String name;
    private LocalDate dueDate;
    private Boolean status;
    public TaskResponseEntity(Long id, String name, LocalDate dueDate, Boolean status) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.status = status;
    }
}
