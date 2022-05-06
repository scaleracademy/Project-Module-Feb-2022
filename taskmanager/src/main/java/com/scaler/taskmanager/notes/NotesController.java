package com.scaler.taskmanager.notes;

import com.scaler.taskmanager.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
@AllArgsConstructor
public class NotesController {
    private NotesService notesService;

    @GetMapping
    ResponseEntity<List<NoteEntity>> getAllNotes(@PathVariable String taskId){
        return ResponseEntity.ok(notesService.getAllNotes(Long.valueOf(taskId)));
    }

    @PostMapping
    ResponseEntity<NoteEntity> createNote(@PathVariable String taskId,@RequestBody CreateNoteRequestBody createNoteRequestBody){
        NoteEntity createdNote = notesService.addNewNote(Long.valueOf(taskId),createNoteRequestBody.getBody());
        return ResponseEntity.created(URI.create(Constants.BASE_URL + "/tasks/" + taskId + "/notes/" + createdNote.getId()))
                .body(createdNote);
    }

    @DeleteMapping("/{noteId}")
    Map<String,Boolean> deleteNote(@PathVariable String noteId){
        notesService.deleteNote(Long.valueOf(noteId));
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }



}
