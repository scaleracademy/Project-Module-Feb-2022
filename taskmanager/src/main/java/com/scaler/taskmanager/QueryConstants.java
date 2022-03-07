package com.scaler.taskmanager;

public final class QueryConstants {

    public static final String FETCH_NOTES_BY_TASKID = "SELECT new com.scaler.taskmanager.notes.dto.NotesResponseBody(" +
            "notes.id, notes.body, notes.task.id) FROM NoteEntity notes " +
            "WHERE notes.task.id = :taskId";

    public static final String DELETE_NOTES_BY_TASKID = "DELETE FROM NoteEntity notes " +
            "WHERE notes.task.id = :taskId" ;

    private QueryConstants() {}
}
