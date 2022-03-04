package com.scaler.todolist.controllers;

import com.scaler.todolist.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;
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
        Task taskToAdd = new Task(task.getId(), task.getName());
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
    ResponseEntity<Task> getTask(int id) {
        for (var task: taskList) {
            if(task.getId()==id) {
                return ResponseEntity.status(200).body(task);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/tasks/{id}")
    ResponseEntity<Task> updateTask(int id, @RequestBody Task body) {
        for (var task: taskList) {
            if(task.getId() == id) {
                // update task and send response
                task.setName(body.getName());
                task.setDone(body.getDone());
                task.setDue(body.getDue());
                return ResponseEntity.status(200).body(task);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping ("/tasks/{id}")
    ResponseEntity<HttpStatus> deleteTask(int id) {
        // Creating iterator object
        Iterator itr = taskList.iterator();

        // Holds true till there is single element
        // remaining in the object
        while (itr.hasNext()) {
            Task x = (Task)itr.next();
            if (x.getId() == id) {
                itr.remove();
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
