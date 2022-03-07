package com.scaler.todolist.controllers;

import com.scaler.todolist.exceptions.ErrorMessages;
import com.scaler.todolist.exceptions.TaskServiceException;
import com.scaler.todolist.models.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable int id){
        int index = id - 1;
        if(index >= taskList.size()) throw new TaskServiceException(ErrorMessages.INVALID_TASK_ID.getErrorMessage());

        return ResponseEntity.status(200).body(taskList.get(index));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task reqBody){
        int index = id - 1;
        if(index >= taskList.size()) throw new TaskServiceException(ErrorMessages.INVALID_TASK_ID.getErrorMessage());

        Task toUpdate = taskList.get(index);
        if(reqBody.getDue() != null)toUpdate.setDue(reqBody.getDue());
        if(reqBody.getDone() != null)toUpdate.setDone(reqBody.getDone());
        taskList.set(index, toUpdate);
        return  ResponseEntity.status(200).body(toUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id){
        int index = id - 1;
        if(index >= taskList.size()) throw new TaskServiceException(ErrorMessages.INVALID_TASK_ID.getErrorMessage());

        taskList.remove(index);

        return ResponseEntity.status(202).body("Task successfully deleted");
    }
}
