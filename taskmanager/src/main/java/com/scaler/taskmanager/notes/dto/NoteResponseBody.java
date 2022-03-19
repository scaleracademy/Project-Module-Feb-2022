package com.scaler.taskmanager.notes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NoteResponseEntity {
    Long id;
    String body;
    Long taskId;
}
