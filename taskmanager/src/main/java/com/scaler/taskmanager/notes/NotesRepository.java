package com.scaler.taskmanager.notes;

import com.scaler.taskmanager.Constants;
import com.scaler.taskmanager.QueryConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<NoteEntity, Long> {

    @Query(value = QueryConstants.FETCH_NOTES_BY_TASKID)
    List<NotesResponseBody> fetchAllByTaskId(@Param(value = "taskId") Long taskId);

    @Query(value = QueryConstants.DELETE_NOTES_BY_TASKID)
    void deleteByTaskId(@Param(value = "taskId") Long taskId);


}
