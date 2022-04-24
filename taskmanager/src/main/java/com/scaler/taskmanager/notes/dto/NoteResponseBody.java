package com.scaler.taskmanager.notes.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class NoteResponseBody {
    Long id;
    String body;
    Long taskId;
}
