package com.scaler.taskmanager.tasks;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TasksService {
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
}
