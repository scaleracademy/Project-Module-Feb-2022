package com.scaler.todolist.controllers;

import com.scaler.todolist.models.DTO.request.CreateTaskRequestDTO;
import com.scaler.todolist.models.DTO.request.UpdateTaskRequestDTO;
import com.scaler.todolist.models.DTO.response.TaskResponseDTO;
import com.scaler.todolist.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/tasks")
@RestController
public class TaskController {

    private final List<Task> taskList = new ArrayList<>();

    @GetMapping("")
    ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        return ResponseEntity.ok(taskList.stream().map(TaskResponseDTO::new).collect(Collectors.toList()));
    }

    @PostMapping("")
    ResponseEntity<TaskResponseDTO> addNewTask(@RequestBody CreateTaskRequestDTO task) {
        Task taskToAdd = new Task(generateTaskId(), task.getName());
        taskList.add(taskToAdd);
        return ResponseEntity.status(201).body(new TaskResponseDTO(taskToAdd));
    }

    private int generateTaskId() {
        if (taskList.size() == 0)
            return 1;
        return taskList.get(taskList.size() - 1).getId() + 1;
    }

    @GetMapping("/{id}")
    ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable(name = "id") String taskIdStr) {
        try {
            int taskId = Integer.parseInt(taskIdStr);
            Task task = findTaskInTaskList(taskId);
            if (task == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task does not exist");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(new TaskResponseDTO(task));
        } catch (NumberFormatException nfe) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "task id should be a number");
        }
    }

    private Task findTaskInTaskList(int taskId) {
        Integer id = findTaskIndexInList(taskId);
        if (id != null) {
            return taskList.get(id);
        }
        return null;
    }

    private Integer findTaskIndexInList(int taskId) {
        for (int i = 0; i < taskList.size(); ++i) {
            Task task = taskList.get(i);
            if (taskId == task.getId()) {
                return i;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTaskById(
            @PathVariable(name = "id") String taskIdStr,
            @RequestBody UpdateTaskRequestDTO updateTaskDTO) {
        try {
            int taskId = Integer.parseInt(taskIdStr);
            Task task = findTaskInTaskList(taskId);
            if (task == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task does not exist");
            }
            if (!(updateTaskDTO.getName() == null)) {
                task.setName(updateTaskDTO.getName());
            }
            if (!(updateTaskDTO.getDone() == null)) {
                task.setDone(updateTaskDTO.getDone());
            }
            if (!(updateTaskDTO.getDue() == null)) {
                task.setDue(updateTaskDTO.getDue());
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new TaskResponseDTO(task));
        } catch (NumberFormatException nfe) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "task id should be a number");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> deleteTaskById(@PathVariable(name = "id") String taskIdStr) {
        try {
            int taskId = Integer.parseInt(taskIdStr);
            Integer taskIndex = findTaskIndexInList(taskId);
            if (taskIndex == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task does not exist");
            }
            Task task = taskList.remove((int) taskIndex);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NumberFormatException nfe) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "task id should be a number");
        }
    }

    /*
     * ASSIGNMENT:
     *  1. GET -> /tasks/3
     *          get task no 3
     *          send 404 error to client if task no 3 does not exist
     *  2. PATCH -> /tasks/2
     *          update due date or done status for task no 2
     *          send 404 error to client if task no 3 does not exist
     *  3. DELETE -> tasks/5
     *          delete task no 5 (response with correct HTTP code)
     *          if task 5 does not exist, send 404
     */


}
