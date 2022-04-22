package com.scaler.taskmanager;

public interface QueryConstants {

    String FETCH_NOTES_BY_TASKID = "SELECT new com.scaler.taskmanager.notes.NotesResponseBody(" +
            "notes.id, notes.body, notes.task.id) FROM NoteEntity notes " +
            "WHERE notes.task.id = :taskId" ;

    String DELETE_NOTES_BY_TASKID = "DELETE FROM NoteEntity notes " +
            "WHERE notes.task.id = :taskId" ;
}
