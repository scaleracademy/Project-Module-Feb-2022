package com.scaler.taskmanagerspringdatarest.notes;

import com.scaler.taskmanagerspringdatarest.tasks.TasksEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity(name = "notes")
public class NotesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String body;

//    Not sure if this is the right way to do it...
//    @NotNull
//    @ManyToOne
//    @JoinColumn(name = "task_id")
//    @RestResource(path = "tasks", rel="tasks")
//    private TasksEntity task;

}
