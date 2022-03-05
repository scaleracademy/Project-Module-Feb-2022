package com.scaler.taskmanager.notes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateNoteRequestBody {

    private Long id;

    private String body;

    private Long taskId;

}
