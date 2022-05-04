package com.scaler.taskmanager.tasks;


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

    public TaskEntity getTaskById(long id) {
        return tasksRepo.findById(id).orElseThrow();
    }

    public void deleteTask(Long taskId) {
        tasksRepo.deleteById(taskId);
    }

    public void updateTask(TaskEntity taskPatched) {
        tasksRepo.save(taskPatched);
    }
}
