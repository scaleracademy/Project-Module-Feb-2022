package com.scaler.taskmanager.notes;

import com.scaler.taskmanager.notes.dto.CreateNoteRequestBody;
import com.scaler.taskmanager.notes.dto.NoteResponseBody;
import com.scaler.taskmanager.tasks.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotesService {

    NotesRepository notesRepo;

    TasksRepository taskRepo;

    public NotesService(NotesRepository notesRepo, TasksRepository taskRepo) {
        this.notesRepo = notesRepo;
        this.taskRepo = taskRepo;
    }

    public NoteResponseBody addNote(CreateNoteRequestBody requestBody, Long taskId) {
        if(taskRepo.existsById(taskId))
            return convertFromEntity(notesRepo.save(new NoteEntity(requestBody.getBody(),taskRepo.getById(taskId))));
        return null;
    }

    public NoteResponseBody convertFromEntity(NoteEntity noteEntity) {
        return NoteResponseBody.builder()
                .body(noteEntity.getBody())
                .id(noteEntity.getId())
                .taskId(noteEntity.getTask().getId())
                .build();
    }

    public List<NoteResponseBody> getAllNotes(Long taskId) {
        return taskRepo.existsById(taskId)? notesRepo.fetchAll(taskId):null;
    }

    public boolean removed(Long taskId,Long notesId) {
        if(taskRepo.existsById(taskId) && notesRepo.existsById(notesId)) {
            notesRepo.deleteById(notesId);
            return true;
        }
        return false;
    }


}
