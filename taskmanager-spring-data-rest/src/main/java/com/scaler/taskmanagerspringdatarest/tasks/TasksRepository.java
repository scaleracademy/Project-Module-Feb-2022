package com.scaler.taskmanagerspringdatarest.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "tasks", path = "tasks")
public interface TasksRepository extends JpaRepository<TasksEntity, Long> {

}
