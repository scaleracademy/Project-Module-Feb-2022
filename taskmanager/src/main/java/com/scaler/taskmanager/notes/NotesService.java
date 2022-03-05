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


    List<NotesResponseBody> getNotesByTask(Long taskId){
        return notesRepository.fetchAllByTaskId(taskId);
    }

    public NotesResponseBody addNote(CreateNoteRequestBody request, Long taskId){
        if(tasksRepository.existsById(taskId))
            return convertFromEntity(notesRepository.save(convertFromCreateRequestBody(request, taskId)));
        else
            return null;
    }

    public NotesResponseBody updateNote(CreateNoteRequestBody request, Long noteId, Long taskId){
        if(tasksRepository.existsById(taskId))
            if(notesRepository.existsById(noteId))
                return convertFromEntity(notesRepository.save(new NoteEntity(noteId, request.getBody(), tasksRepository.getById(taskId))));

        return null;
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

