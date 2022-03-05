package com.scaler.taskmanager.tasks;

import com.scaler.taskmanager.Constants;
import com.scaler.taskmanager.notes.CreateNoteRequestBody;
import com.scaler.taskmanager.notes.NotesResponseBody;
import com.scaler.taskmanager.notes.NotesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
        List<TaskEntity> response = tasksService.getAllTasks();
        return !response.isEmpty() ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
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
        TaskResponseBody response = tasksService.updateTask(requestBody);
        return null != response ? ResponseEntity.accepted().body(response) : ResponseEntity.notFound().build();
    }


    @DeleteMapping(path = "/{id}")
    ResponseEntity<Long> deleteTask(@PathVariable Long id){
        boolean removed = tasksService.delete(id);
        if(!removed)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.accepted().body(id);
    }

}
