package com.scaler.taskmanager.notes;

import com.scaler.taskmanager.ResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteResponseDTO extends ResponseDTO {
    NoteEntity note;
}
