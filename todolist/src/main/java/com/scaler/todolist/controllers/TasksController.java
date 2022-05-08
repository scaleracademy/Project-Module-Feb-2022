package com.scaler.todolist.controllers;

import com.scaler.todolist.models.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
    ResponseEntity<Task> getTask(@PathVariable("id") int id){
        if(id<0 || id>=taskList.size())
            return (ResponseEntity<Task>) ResponseEntity.notFound();
        return ResponseEntity.ok(taskList.get(id));
    }

    @PatchMapping("/{id}")
    ResponseEntity<Task> updateTask(@PathVariable("id") int id){
        if(id<0 || id>=taskList.size())
            return (ResponseEntity<Task>) ResponseEntity.notFound();
        taskList.get(id).setDone(true);
        return ResponseEntity.ok(taskList.get(id));
    }

    @RequestMapping(value = "/{id}",method = {RequestMethod.DELETE})
    void deleteTask(@PathVariable("id") int id){
        if(id<0 || id>=taskList.size())
            return;
        taskList.remove(id);
    }

}
