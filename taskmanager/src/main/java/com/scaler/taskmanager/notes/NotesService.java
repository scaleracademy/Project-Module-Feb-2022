package com.scaler.taskmanager.notes;

import com.scaler.taskmanager.tasks.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private TasksRepository tasksRepository;

    public NotesResponseBody getNoteById(Long id){
        return convertFromEntity(notesRepository.getById(id));
    }

    public NotesResponseBody getParticularNote(Long id){
        return convertFromEntity(notesRepository.getById(id));
    }


    public NotesResponseBody addNote(CreateNoteRequestBody request, Long taskId){
        return convertFromEntity(notesRepository.save(convertFromCreateRequestBody(request, taskId)));
    }

    public NotesResponseBody addNote(UpdateNoteRequestBody request){
        return convertFromEntity(notesRepository.save(convertFromUpdateRequestBody(request)));
    }


    public NotesResponseBody convertFromEntity(NoteEntity entity){
        return new NotesResponseBody.NotesResponseBodyBuilder().
                id(entity.getId()).
                body(entity.getBody()).
                taskId(entity.getTask().getId()).build();
    }

    public NoteEntity convertFromCreateRequestBody(CreateNoteRequestBody requestBody, Long taskId){
        return new NoteEntity(requestBody.getBody(), tasksRepository.getById(taskId));
    }

    public NoteEntity convertFromUpdateRequestBody(UpdateNoteRequestBody requestBody){
        return new NoteEntity(requestBody.getId(), requestBody.getBody(), tasksRepository.getById(requestBody.getTaskId()));
    }

    public boolean delete(Long id, Long taskId) {
        if(notesRepository.fetchAllByTaskId(taskId).size() > 0)
            if(notesRepository.existsById(id)) {
                notesRepository.deleteById(id);
                return true;
            }

        return false;
    }

    public boolean deleteByTask(Long id){

        if(notesRepository.fetchAllByTaskId(id).size() > 0) {
            notesRepository.deleteAllByIdInBatch(notesRepository.fetchAllByTaskId(id).stream().map(NotesResponseBody::getId).collect(Collectors.toList()));
            return true;
        }

        return false;
    }
}

