package com.scaler.taskmanagerspringdatarest.tasks;

import com.scaler.taskmanagerspringdatarest.notes.NotesEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Getter
@Entity(name = "tasks")
public class TasksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    private LocalDate dueDate;

    private Boolean status;

    @OneToMany
    private List<NotesEntity> notes;


    public void setName(String name){
        this.name = name;
        this.dueDate = LocalDate.now().plusDays(7L);
        this.status = false;
    }
}

