package com.scaler.taskmanagerspringdatarest.notes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "notes", path = "notes")
public interface NotesRepository extends JpaRepository<NotesEntity, Long> {

}
