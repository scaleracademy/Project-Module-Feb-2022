package com.scaler.taskmanager.tasks;

import com.scaler.taskmanager.Constants;
import com.scaler.taskmanager.notes.CreateNoteRequestBody;
import com.scaler.taskmanager.notes.NotesResponseBody;
import com.scaler.taskmanager.notes.NotesService;
import com.scaler.taskmanager.notes.UpdateNoteRequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/tasks")
@RestController
public class TasksController {
    private TasksService tasksService;

    private NotesService notesService;

    public TasksController(TasksService tasksService, NotesService notesService) {
        this.tasksService = tasksService;
        this.notesService = notesService;
    }

    @GetMapping("")
    ResponseEntity<List<TaskEntity>> getAllTasks() {
        return ResponseEntity.ok(tasksService.getAllTasks());
    }

    @PostMapping("")
    ResponseEntity<TaskEntity> createTask(@RequestBody CreateTaskRequestBody body) {

        TaskEntity savedTask = tasksService.addNewTask(body.name);

        return ResponseEntity.created(
                URI.create(Constants.BASE_URL + "/tasks/" + savedTask.id)
        ).body(savedTask);
    }

    @PutMapping(path = "")
    ResponseEntity<TaskResponseBody> updateTask(@RequestBody UpdateTaskRequestBody requestBody){
        return ResponseEntity.accepted().body(tasksService.updateTask(requestBody));
    }


    @PostMapping(path = "/{id}/notes")
    ResponseEntity<NotesResponseBody> createNotes(@PathVariable Long id, @RequestBody CreateNoteRequestBody requestBody){
        NotesResponseBody responseBody = notesService.addNote(requestBody, id);
        return ResponseEntity.created(
                URI.create(Constants.BASE_URL + "/notes/" + responseBody.getId())
        ).body(responseBody);
    }

    @GetMapping(path = "/{id}/notes")
    ResponseEntity<List<NotesResponseBody>> getNotesByTaskId(@PathVariable Long id){
        List<NotesResponseBody> response = tasksService.getNotesByTask(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Long> deleteTask(@PathVariable Long id){
        boolean removed = tasksService.delete(id);
        if(!removed)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.accepted().body(id);
    }

    @DeleteMapping(path = "/{id}/notes")
    ResponseEntity<Long> deleteNotesByTask(@PathVariable Long id){
        boolean removed = notesService.deleteByTask(id);
        if(!removed)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.accepted().body(id);
    }

    @DeleteMapping(path = "/{taskid}/notes/{noteid}")
    ResponseEntity<Map<Long, Long>> deleteParticularNoteForTask(@PathVariable(name = "taskid") Long taskId, @PathVariable(name = "noteid") Long noteId){
        boolean removed = notesService.delete(noteId, taskId);
        if(!removed)
            return ResponseEntity.notFound().build();
        else{
            Map<Long, Long> response = new HashMap<>();
            response.put(taskId, noteId);
            return ResponseEntity.accepted().body(response);
        }
    }

}
