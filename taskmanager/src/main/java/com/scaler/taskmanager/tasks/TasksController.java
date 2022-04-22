package com.scaler.taskmanager.tasks;

import com.scaler.taskmanager.Constants;
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
    ResponseEntity<TaskResponseDTO> getById(@PathVariable Long id){
        TaskResponseDTO taskResponseDTO = tasksService.getById(id);
        return ResponseEntity.status(taskResponseDTO.getStatus()).body(taskResponseDTO);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<TaskResponseDTO> deleteById(@PathVariable Long id){
        TaskResponseDTO taskGetResponse = tasksService.getById(id);
        if(taskGetResponse.getStatus() == 404){
            return ResponseEntity.status(taskGetResponse.getStatus()).body(taskGetResponse);
        }

        tasksService.deleteById(id);
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setStatus(200);
        taskResponseDTO.setMessage("Task deleted successfully.");

        return ResponseEntity.ok(taskResponseDTO);
    }

    @PatchMapping("/{id}")
    ResponseEntity<TaskResponseDTO> updateById(@PathVariable Long id,@RequestBody UpdateTaskRequestBody updateTaskRequestBody){
        TaskResponseDTO taskResponseDTO = tasksService.updateById(id,updateTaskRequestBody);
        return ResponseEntity.status(taskResponseDTO.getStatus()).body(taskResponseDTO);
    }
}
