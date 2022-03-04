package com.scaler.taskmanager.tasks;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
