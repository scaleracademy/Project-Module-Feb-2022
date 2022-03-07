package com.scaler.todolist.controllers;

import com.scaler.todolist.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
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
    /*
     *Assignment
     1. GET -> /tasks/3
     get task no 3
     2.Patch -> task/3
     update due date or done status for task no 3
     send 404 error to client if task no 3 does not exist
     3. Delete -> tasks/5
     delete task no 5 (response code with correct http code)
     if task 5 does not exist
     */
    @GetMapping("/{id}")
    ResponseEntity<Task> getTaskNumber(@PathVariable("id") int id){
        if(taskList.size() == 0 || id > taskList.size() ){//Add a length check
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(taskList.get(id));
    }

    @PatchMapping("/{id}")
    ResponseEntity<Task> updateTask(@PathVariable("id") int id, @RequestBody Task task){
        if(taskList.size() == 0 || id > taskList.size() ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Task taskToUpdate = taskList.get(id);
        taskToUpdate.setDone(true);
        return ResponseEntity.status(200).body(taskToUpdate);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteTask(@PathVariable("id") int id, @RequestBody Task task){
        if(taskList.size() == 0 || id > taskList.size() ){
            return new ResponseEntity("Task to delete not found",HttpStatus.NOT_FOUND);
        }
        Task taskToDelete = taskList.get(id);
        taskList.remove(id);
        return new ResponseEntity("Task deleted successful", HttpStatus.OK);
    }


}
