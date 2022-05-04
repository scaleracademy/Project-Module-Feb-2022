package com.scaler.taskmanager.notes;

import com.scaler.taskmanager.tasks.TaskEntity;
import com.scaler.taskmanager.tasks.TasksService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotesService {
    private NotesRepository notesRepository;

    @Autowired
    private TasksService tasksService;

    public List<NoteEntity> getAllNotes(Long taskId) {
        return notesRepository.findAllByTaskId(taskId);
    }

    public NoteEntity addNewNote(Long taskId,String noteBody){
        TaskEntity parentTask = tasksService.getTaskById(taskId);
        NoteEntity note = new NoteEntity(noteBody,parentTask);
        NoteEntity savedNote = notesRepository.save(note);
        return savedNote;
    }

    public void deleteNote(Long noteId){
        notesRepository.deleteById(noteId);
    }
}
