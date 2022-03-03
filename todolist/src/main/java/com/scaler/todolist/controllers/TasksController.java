package com.scaler.todolist.controllers;

import com.scaler.todolist.models.Task;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

     @GetMapping("/tasks/{id}")
     public ResponseEntity<Task> getTaskById(@PathVariable String id){
         Task t = findTaskById(id);
         if(t!=null){
             return ResponseEntity.status(404).body(t);
         }else{
             return ResponseEntity.ok(t);
         }

         
     }
     private Task findTaskById(String id){
        for(Task t: taskList){
            if(t.getName().equals(id)){
                return t;
            }
        }
        return null;
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity updateTaskById(@PathVariable String id){
        Task t = findTaskById(id);
        if(t!=null){
            t.setDue(LocalDate.now().plus(5, ChronoUnit.DAYS));
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }   

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity deleteTaskById(@PathVariable String id){
        Task t = findTaskById(id);
        if(t!=null){
            taskList.remove(t);
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    } 


}
