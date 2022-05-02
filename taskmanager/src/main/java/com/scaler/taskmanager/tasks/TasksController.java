package com.scaler.taskmanager.tasks;

import com.scaler.taskmanager.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.NoSuchElementException;

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
    ResponseEntity getTaskById(@PathVariable String id){
        TaskEntity task;
        try{
            task = tasksService.getTaskById(Long.valueOf(id));
        }
        catch(NumberFormatException e){
            return ResponseEntity.badRequest().build();
        }
        catch(NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);

    }



}
