package com.scaler.taskmanager.notes;

import com.scaler.taskmanager.tasks.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "notes")
public class NoteEntity  {
    @Id
    Integer id;
    String body;

    @ManyToOne
    TaskEntity task;
}
