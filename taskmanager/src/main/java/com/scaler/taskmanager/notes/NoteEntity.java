package com.scaler.taskmanager.notes;

import com.scaler.taskmanager.tasks.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "notes")
public class NoteEntity  {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(nullable = false)
    String body;

    @ManyToOne
    TaskEntity task;

    public NoteEntity(String body, TaskEntity task){
        this.body = body;
        this.task = task;
    }
}
