package com.scaler.taskmanager.notes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<NoteEntity,Long> {
    NoteEntity save(NoteEntity note);

    List<NoteEntity> findByTaskId(Long aLong);
}
