package com.scaler.taskmanager.tasks.dto;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TaskResponseEntity {
    private Long id;
    private String name;
    private LocalDate dueDate;
    private Boolean status;

}
