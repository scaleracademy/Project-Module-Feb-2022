package com.scaler.taskmanager.tasks.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;


@Getter
@Builder
public class TaskResponseBody {

    private Long id;

    private String name;

    private LocalDate dueDate;

    private Boolean status;

}
