package com.scaler.taskmanager.notes;

import com.scaler.taskmanager.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/tasks/{id}/notes")
public class NotesController {

    @Autowired
    NotesService notesService;

    @PostMapping(path = "")
    ResponseEntity<NotesResponseBody> createNotes(@PathVariable Long id, @RequestBody CreateNoteRequestBody requestBody){
        NotesResponseBody responseBody = notesService.addNote(requestBody, id);
        return null != responseBody ? ResponseEntity.created(
                URI.create(Constants.BASE_URL + "/notes/" + responseBody.getId())
        ).body(responseBody) : ResponseEntity.notFound().build();
    }

    @GetMapping(path = "")
    ResponseEntity<List<NotesResponseBody>> getNotesByTaskId(@PathVariable Long id){
        List<NotesResponseBody> response = notesService.getNotesByTask(id);
        return !response.isEmpty() ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/{noteid}")
    ResponseEntity<NotesResponseBody> updateNote(@PathVariable Long id, @PathVariable Long noteid, @RequestBody CreateNoteRequestBody requestBody){
        NotesResponseBody response = notesService.updateNote(requestBody, noteid, id);
        return null != response ?ResponseEntity.accepted().body(response) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "")
    ResponseEntity<Long> deleteNotesByTask(@PathVariable Long id){
        boolean removed = notesService.deleteByTask(id);
        if(!removed)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.accepted().body(id);
    }


    @DeleteMapping(path = "/{noteid}")
    ResponseEntity<Map<Long, Long>> deleteParticularNoteForTask(@PathVariable Long id, @PathVariable Long noteid){
        boolean removed = notesService.delete(noteid, id);
        if(!removed)
            return ResponseEntity.notFound().build();
        else{
            Map<Long, Long> response = new HashMap<>();
            response.put(id, noteid);
            return ResponseEntity.accepted().body(response);
        }
    }
}
