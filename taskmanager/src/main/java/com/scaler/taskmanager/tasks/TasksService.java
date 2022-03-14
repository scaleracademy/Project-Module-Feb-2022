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

    public List<TaskEntity> getAllTasks() {
        return tasksRepo.findAll();
    }

    public TaskEntity addNewTask(String taskName) {
        TaskEntity task = new TaskEntity(taskName);
        TaskEntity savedTask = tasksRepo.save(task);
        return savedTask;
    }

    public TaskEntity getTaskById(Long id) {
        return tasksRepo.getById(id);
    }

    public boolean deleteTaskById(Long taskId) {

        if(tasksRepo.existsById(taskId)){
            tasksRepo.deleteById(taskId);
            return true;
        }
        return false;
    }

}
