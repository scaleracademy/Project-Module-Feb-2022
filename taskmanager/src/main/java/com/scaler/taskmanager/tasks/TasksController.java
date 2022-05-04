package com.scaler.taskmanager.tasks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.scaler.taskmanager.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @DeleteMapping("/{id}")
    Map<String,Boolean> deleteTaskById(@PathVariable String id){
        tasksService.deleteTask(Long.valueOf(id));
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

    @PatchMapping(value = "/{id}", consumes = "application/json-patch+json")
    ResponseEntity<TaskEntity> updateUser(@PathVariable String id, @RequestBody JsonPatch patch){
        try{
            TaskEntity task = tasksService.getTaskById(Long.valueOf(id));
            TaskEntity taskPatched = applyPatchToTask(patch, task);
            tasksService.updateTask(taskPatched);
            return ResponseEntity.ok(taskPatched);
        } catch (JsonPatchException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    private TaskEntity applyPatchToTask(JsonPatch patch, TaskEntity task) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        JsonNode patched = patch.apply(objectMapper.convertValue(task, JsonNode.class));
        return objectMapper.treeToValue(patched, TaskEntity.class);
    }

}
