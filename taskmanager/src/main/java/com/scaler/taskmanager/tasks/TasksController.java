package com.scaler.taskmanager.tasks;

import com.scaler.taskmanager.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpResponse;
import java.util.List;

@RequestMapping("/tasks")
@RestController
public class TasksController {
    private TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
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

    @GetMapping("/{id}")
    ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Long taskId) {
        TaskEntity task = tasksService.getTaskById(taskId);
        if(task != null) {
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpResponse> deleteTaskById(@PathVariable("id") Long taskId) {
        if(tasksService.deleteTaskById(taskId))
        {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
