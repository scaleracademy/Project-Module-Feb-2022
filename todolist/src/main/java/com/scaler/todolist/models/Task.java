package com.scaler.todolist.models;

import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
//@Entity(name ="tasks")
public class Task {
    //@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private LocalDate due;
    private Boolean done;

    /**
     * Creates a task object with given task string,
     * due date 5 days from now, and done = false
     * @param name
     */
    public Task(Long id,String name) {
        this.id = id;
        this.name = name;
        due = LocalDate.now().plus(5, ChronoUnit.DAYS);
        done = false;
    }
}
