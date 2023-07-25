package com.satyam.taskmanager.repository;

import com.satyam.taskmanager.model.NoteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<NoteModel,Long> {
}
