package com.scaler.taskmanager.notes;

import com.scaler.taskmanager.tasks.UpdateTaskRequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/notes")
public class NotesController {

    private final NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<NotesResponseBody> getNoteById(@PathVariable Long id){
        return ResponseEntity.ok(notesService.getNoteById(id));
    }

    @PutMapping(path = "")
    ResponseEntity<NotesResponseBody> updateNote(@RequestBody UpdateNoteRequestBody requestBody){
        NotesResponseBody response = notesService.addNote(requestBody);
        return ResponseEntity.accepted().body(response);
    }
}
