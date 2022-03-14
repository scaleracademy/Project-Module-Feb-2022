package com.scaler.todolist.controllers;

import com.scaler.todolist.models.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        Task taskToAdd = new Task(task.getId(),task.getName());
        taskList.add(taskToAdd);
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

    @GetMapping("/{id}")
    ResponseEntity<Task> getTaskById(@PathVariable("id") Long taskId) {
        for(Task taskItr: taskList) {
            if(taskItr.getId() == taskId) {
                return ResponseEntity.ok(taskItr);
            }
        }
        return ResponseEntity.status(404).body(null);
    }

    @PatchMapping("/{id}")
    ResponseEntity<Task> updateTaskById(@PathVariable("id") Long taskId,@RequestBody Task task) {
        for(Task taskItr:taskList) {
            if(taskItr.getId() == taskId) {
                taskItr.setDone(task.getDone());
                taskItr.setDue(task.getDue());
                return ResponseEntity.ok(taskItr);
            }
        }
        return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteTaskById(@PathVariable("id") Long taskId) {
        for(Task task:taskList) {
            if(task.getId() == taskId) {
                taskList.remove(task);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.status(404).body(null);
    }

}
