package com.edron.demo.repositories;

import com.edron.demo.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    TaskEntity findTaskEntitiesById(Long id);
    List<TaskEntity> findTaskEntitiesByState(String state);

}
