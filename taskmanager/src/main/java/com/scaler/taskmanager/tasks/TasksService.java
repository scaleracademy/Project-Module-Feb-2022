package com.scaler.taskmanager.tasks;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TasksService {
    @Autowired
    private TasksRepository tasksRepo;

    public TasksService(TasksRepository tasksRepo) {
        this.tasksRepo = tasksRepo;
    }

    List<TaskEntity> getAllTasks() {
        return tasksRepo.findAll();
    }

    TaskEntity addNewTask(String taskName) {
        TaskEntity task = new TaskEntity(taskName);
        TaskEntity savedTask = tasksRepo.save(task);
        return savedTask;
    }

    TaskResponseDTO getById(Long id) {

        Optional<TaskEntity> task = tasksRepo.findById(id);

        TaskResponseDTO response = new TaskResponseDTO();

        if(task.isEmpty() == true){
            response.setStatus(404);
            response.setMessage("Task not found.");
        }else{
            response.setStatus(200);
            response.setMessage("Task found.");
            response.setTask(task.get());
        }

        return response;
    }

    void deleteById(Long id){
        tasksRepo.deleteById(id);
    }

    TaskResponseDTO updateById(Long id, UpdateTaskRequestBody updateTaskRequestBody){
        Optional<TaskEntity> task = tasksRepo.findById(id);

        TaskResponseDTO response = new TaskResponseDTO();

        if(task.isEmpty() == true){
            response.setStatus(404);
            response.setMessage("Task not found.");
        }else{
            TaskEntity updateTask = task.get();
            if(updateTaskRequestBody.getDueDate() != null){
                updateTask.setDueDate(updateTaskRequestBody.getDueDate());
            }
            if(updateTaskRequestBody.getStatus() != null){
                updateTask.setStatus(updateTaskRequestBody.getStatus());
            }

            tasksRepo.save(updateTask);
            response.setStatus(200);
            response.setMessage("Task updated succesfully.");
            response.setTask(updateTask);
        }

        return response;
    }
}
