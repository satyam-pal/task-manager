package com.satyam.taskmanager.repository;

import com.satyam.taskmanager.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskModel,Long> {

    List<TaskModel> findByIsCompleted(Boolean isCompleted);
}
