package com.scaler.taskmanager.tasks.dto;

import com.scaler.taskmanager.tasks.TaskEntity;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
public class UpdateTaskRequestBody {
            private Long id;
            private String name;
            private LocalDate dueDate;
            private Boolean status;

             public TaskEntity convertFromUpdateRequest(UpdateTaskRequestBody updateRequest, TaskEntity taskEntity) {
                if(updateRequest.getName() != null)
                    taskEntity.setName(updateRequest.getName());
                if(updateRequest.getDueDate() != null)
                    taskEntity.setDueDate(updateRequest.getDueDate());
                if(updateRequest.getStatus() != null)
                    taskEntity.setStatus(updateRequest.getStatus());
                return taskEntity;
            }
}
