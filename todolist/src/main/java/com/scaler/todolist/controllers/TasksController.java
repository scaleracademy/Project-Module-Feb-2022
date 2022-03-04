package com.scaler.todolist.controllers;

import com.scaler.todolist.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.TreeMap;

@RequestMapping("/tasks")
@RestController
public class TasksController {

    private Integer idCounter = 0;
    private TreeMap<Integer, Task> taskMap;

    @GetMapping("/")
    ResponseEntity<Collection<Task>> getAllTasks() {
        return ResponseEntity.ok(taskMap.values());
    }

    @PostMapping("/")
    ResponseEntity<Task> addNewTask(@RequestBody Task task) {
        Task taskToAdd = new Task(idCounter++, task.getName());
        taskMap.put(taskToAdd.getId(), taskToAdd);
        return ResponseEntity.status(201).body(taskToAdd);
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
    @GetMapping("/:id")
    ResponseEntity<Task> getTask(@RequestBody Task task) {
        if(taskMap.containsKey(task.getId())) {
            return ResponseEntity.status(200).body(taskMap.get(task.getId()));
        }
        return ResponseEntity.status(404).body(new Task());
    }

    @PatchMapping("/:id")
    ResponseEntity<String> updateTask(@RequestBody Task task) {
        if(taskMap.containsKey(task.getId())) {
            taskMap.get(task.getId()).setName(task.getName());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Task successfully updated");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
    }

    @DeleteMapping("/:id")
    ResponseEntity<String> deleteTask(@RequestBody Task task) {
        if(taskMap.containsKey(task.getId())) {
            taskMap.remove(task.getId());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
    }
}
