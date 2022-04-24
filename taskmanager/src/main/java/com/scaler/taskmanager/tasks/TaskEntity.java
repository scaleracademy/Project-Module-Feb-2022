package com.scaler.taskmanager.tasks;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Entity(name = "tasks")
public class TaskEntity {

    public TaskEntity(String name) {
        this.name = name;
        this.dueDate = LocalDate.now().plus(7, ChronoUnit.DAYS);
        this.status = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    LocalDate dueDate;

    @Column(columnDefinition = "boolean default false")
    Boolean status;
}
