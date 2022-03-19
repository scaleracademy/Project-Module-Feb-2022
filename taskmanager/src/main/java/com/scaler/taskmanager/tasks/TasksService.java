package com.scaler.taskmanager.tasks;


import com.scaler.taskmanager.tasks.dto.TaskResponseEntity;
import com.scaler.taskmanager.tasks.dto.UpdateTaskRequestBody;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TasksService {
    private TasksRepository tasksRepo;

     TasksService(TasksRepository tasksRepo) {
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

     TaskEntity getTaskById(Long id) {
        return tasksRepo.getById(id);
    }

    boolean deleteTaskById(Long taskId) {

        if(tasksRepo.existsById(taskId)){
            tasksRepo.deleteById(taskId);
            return true;
        }
        return false;
    }

     TaskResponseEntity updateTaskById(Long taskId, UpdateTaskRequestBody requestBody) {
        return tasksRepo.existsById(taskId)?
                convertFromEntity(tasksRepo
                        .save(requestBody.convertFromUpdateRequest(requestBody,tasksRepo.getById(taskId)))):null;
    }


     TaskResponseEntity convertFromEntity(TaskEntity task) {
         return TaskResponseEntity.builder()
                 .id(task.getId())
                 .name(task.getName())
                 .dueDate(task.getDueDate())
                 .status(task.getStatus())
                 .build();
     }

}
