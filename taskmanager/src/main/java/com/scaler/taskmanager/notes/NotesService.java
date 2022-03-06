package com.scaler.taskmanager.notes;

import com.scaler.taskmanager.tasks.TaskResponseDTO;
import com.scaler.taskmanager.tasks.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {
    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private TasksService tasksService;

    NoteResponseDTO create(Long taskId, CreateNoteRequestBody createNoteRequestBody){

        TaskResponseDTO taskResponseDTO = this.tasksService.getById(taskId);
        NoteResponseDTO noteResponseDTO = new NoteResponseDTO();

        if(taskResponseDTO.getStatus() == 404){
            noteResponseDTO.setStatus(404);
            noteResponseDTO.setMessage("Task Not Found.");
        }else{
            NoteEntity note = new NoteEntity();
            note.setBody(createNoteRequestBody.getBody());
            note.setTask(taskResponseDTO.getTask());

            NoteEntity createdNote = notesRepository.save(note);
            noteResponseDTO.setStatus(201);
            noteResponseDTO.setMessage("Note created.");
            noteResponseDTO.setNote(createdNote);
        }

        return noteResponseDTO;
    }

    List<NoteEntity> getAllByTaskId(Long taskId){
        return notesRepository.findByTaskId(taskId);
    }
}
