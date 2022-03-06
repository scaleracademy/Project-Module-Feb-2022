package com.scaler.taskmanager.notes;

import com.scaler.taskmanager.tasks.TaskEntity;
import com.scaler.taskmanager.tasks.TaskResponseDTO;
import com.scaler.taskmanager.tasks.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public NoteResponseDTO getById(Long id) {

        Optional<NoteEntity> note = notesRepository.findById(id);

        NoteResponseDTO response = new NoteResponseDTO();

        if(note.isEmpty() == true){
            response.setStatus(404);
            response.setMessage("Note not found.");
        }else{
            response.setStatus(200);
            response.setMessage("Note found.");
            response.setNote(note.get());
        }

        return response;
    }

    NoteResponseDTO deleteByTaskIdAndNoteId(Long taskId, Long noteId){
        TaskResponseDTO taskResponseDTO = this.tasksService.getById(taskId);
        NoteResponseDTO noteResponseDTO = getById(noteId);
        NoteResponseDTO responseDTO = new NoteResponseDTO();

        if(taskResponseDTO.getStatus() == 404){
            responseDTO.setStatus(404);
            responseDTO.setMessage("Task Not Found.");
        }else if(noteResponseDTO.getStatus() == 404){
            responseDTO.setStatus(404);
            responseDTO.setMessage("Note Not Found.");
        }else{
            notesRepository.deleteById(noteId);
            responseDTO.setStatus(200);
            responseDTO.setMessage("Note deleted successfully.");
            responseDTO.setNote(noteResponseDTO.getNote());
        }

        return responseDTO;
    }
}
