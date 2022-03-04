package com.scaler.todolist.models;

import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Task {
    private int id;
    private String name;
    private LocalDate due;
    private Boolean done;

    /**
     * Creates a task object with given task string,
     * due date 5 days from now, and done = false
     * @param name
     */
    public Task(int id, String name) {
        this(id, name, LocalDate.now().plus(5, ChronoUnit.DAYS), false);
    }
}
