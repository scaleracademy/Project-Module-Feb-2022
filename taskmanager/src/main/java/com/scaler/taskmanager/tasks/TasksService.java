package com.scaler.taskmanager.tasks;


import com.scaler.taskmanager.notes.NotesRepository;
import com.scaler.taskmanager.notes.NotesService;
import com.scaler.taskmanager.tasks.dto.TaskResponseBody;
import com.scaler.taskmanager.tasks.dto.UpdateTaskRequestBody;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksService {
    private TasksRepository tasksRepo;

    private NotesRepository notesRepository;

    private NotesService notesService;

    public TasksService(TasksRepository tasksRepo, NotesRepository notesRepository, NotesService notesService) {
        this.tasksRepo = tasksRepo;
        this.notesRepository = notesRepository;
        this.notesService = notesService;
    }

    List<TaskEntity> getAllTasks() {
        return tasksRepo.findAll();
    }

    TaskEntity addNewTask(String taskName) {
        TaskEntity task = new TaskEntity(taskName);
        TaskEntity savedTask = tasksRepo.save(task);
        return savedTask;
    }


    TaskResponseBody updateTask(UpdateTaskRequestBody requestBody) {
        return tasksRepo.existsById(requestBody.getId()) ? convertFromEntity(tasksRepo.save(convertFromUpdateRequestBody(requestBody)))
                : null;
    }

    TaskEntity convertFromUpdateRequestBody(UpdateTaskRequestBody requestBody) {
        return new TaskEntity(requestBody.getId(), requestBody.getName(), requestBody.getDueDate(), requestBody.getStatus());
    }

    TaskResponseBody convertFromEntity(TaskEntity entity) {
        return TaskResponseBody.builder()
                .id(entity.getId())
                .name(entity.getName())
                .dueDate(entity.getDueDate())
                .status(entity.getStatus())
                .build();
    }


    public boolean delete(Long id) {
        if (tasksRepo.existsById(id)) {
            tasksRepo.deleteById(id);
            notesService.deleteByTask(id);
            return true;
        } else
            return false;
    }
}
