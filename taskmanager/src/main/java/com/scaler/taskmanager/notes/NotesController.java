package com.scaler.taskmanager.notes;


import com.scaler.taskmanager.Constants;
import com.scaler.taskmanager.notes.dto.CreateNoteRequestBody;
import com.scaler.taskmanager.notes.dto.NoteResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks/{id}/notes/")
public class NotesController {

    private NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @PostMapping("")
    ResponseEntity<NoteResponseBody> createNotes(@PathVariable("id") Long taskId,@RequestBody CreateNoteRequestBody body) {
        NoteResponseBody note = notesService.addNote(body,taskId);
        return note == null? ResponseEntity.notFound().build():ResponseEntity.created(
                URI.create(Constants.BASE_URL + "/notes" + note.getId()))
                .body(note);
    }

    @GetMapping("")
    ResponseEntity<List<NoteResponseBody>> getAllNotes(@PathVariable("id") Long taskId) {
        List<NoteResponseBody> noteResponseList = notesService.getAllNotes(taskId);
        return noteResponseList == null ? ResponseEntity.notFound().build():ResponseEntity.ok(noteResponseList);
    }

    @DeleteMapping("/{noteId}")
    ResponseEntity<Map<Long,Long>> deleteParticularNote(@PathVariable Long id, @PathVariable Long noteId) {
        boolean removed = notesService.removed(id,noteId);
        if(removed) {
            Map<Long,Long> response = new HashMap<>();
            response.put(id,noteId);
            return ResponseEntity.ok(response);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }



}
