package com.scaler.todolist.controllers;

import com.scaler.todolist.DTO.TaskResponseDTO;
import com.scaler.todolist.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/tasks")
@RestController
public class TasksController {

    private ArrayList<Task> taskList = new ArrayList<>();

    @GetMapping("/")
    ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskList);
    }

    @PostMapping("/")
    ResponseEntity<Task> addNewTask(@RequestBody Task task) {
        Task taskToAdd = new Task(task.getName());
        taskList.add(taskToAdd);
        return ResponseEntity.status(201).body(taskToAdd);
    }

    @GetMapping("/{id}")
    ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable int id){
        // If Index out of Bounds, return Not found
        if(taskList.size() <= id){
            TaskResponseDTO body = new TaskResponseDTO();
            body.setStatus(404);
            body.setMessage("Task Not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
        Task task = taskList.get(id);
        TaskResponseDTO body = new TaskResponseDTO();
        body.setStatus(200);
        body.setMessage("Task updated.");
        body.setTask(task);
        return ResponseEntity.ok(body);
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
