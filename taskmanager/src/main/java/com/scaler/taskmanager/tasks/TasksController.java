package com.scaler.taskmanager.tasks;

import com.scaler.taskmanager.Constants;
import com.scaler.taskmanager.tasks.dto.CreateTaskRequestBody;
import com.scaler.taskmanager.tasks.dto.TaskResponseEntity;
import com.scaler.taskmanager.tasks.dto.UpdateTaskRequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        TaskEntity savedTask = tasksService.addNewTask(body.getName());

        return ResponseEntity.created(
                URI.create(Constants.BASE_URL + "/tasks/" + savedTask.id)
        ).body(savedTask);
    }

    @GetMapping("/{id}")
    ResponseEntity<TaskResponseEntity> getTaskById(@PathVariable("id") Long taskId) {
        TaskEntity task = tasksService.getTaskById(taskId);
        if(task != null) {
            return ResponseEntity.ok(new TaskResponseEntity(task.getId(),task.getName(),task.getDueDate(),task.getStatus()));
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

    @PatchMapping("/{id}")
    ResponseEntity<TaskResponseEntity> updateTaskById(@PathVariable("id") Long taskId, @RequestBody UpdateTaskRequestBody updateEntity) {
        return ResponseEntity.ok(tasksService.updateTaskById(taskId, updateEntity));
    }
}
