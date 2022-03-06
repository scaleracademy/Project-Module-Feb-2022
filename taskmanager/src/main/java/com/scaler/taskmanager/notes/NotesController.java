package com.scaler.taskmanager.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class NotesController {
    @Autowired
    private NotesService notesService;

    @PostMapping("/tasks/{id}/notes")
    ResponseEntity<NoteResponseDTO> create(@PathVariable Long id, @RequestBody CreateNoteRequestBody createNoteRequestBody){
        NoteResponseDTO noteResponseDTO = notesService.create(id,createNoteRequestBody);
        return ResponseEntity.status(noteResponseDTO.getStatus()).body(noteResponseDTO);
    }

    @GetMapping("/tasks/{id}/notes")
    ResponseEntity<List<NoteEntity>> getAllByTaskId(@PathVariable Long id){
        return ResponseEntity.ok(notesService.getAllByTaskId(id));
    }
}
