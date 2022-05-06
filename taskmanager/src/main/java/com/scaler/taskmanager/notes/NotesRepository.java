package com.scaler.taskmanager.notes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotesRepository extends JpaRepository<NoteEntity,Long> {
    List<NoteEntity> findAllByTaskId(Long taskId);
}
