package com.scaler.taskmanager.notes;

import lombok.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotesResponseBody {

    private Long id;

    private String body;

    private Long taskId;

}
