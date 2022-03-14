package com.scaler.taskmanager.notes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  NotesRepository  extends JpaRepository<NoteEntity,Long> {

}
