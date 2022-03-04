package com.scaler.taskmanager.tasks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "tasks")
public class TaskEntity {

    @Id
    Integer id;
    String name;
    LocalDate dueDate;
    Boolean status;
}
