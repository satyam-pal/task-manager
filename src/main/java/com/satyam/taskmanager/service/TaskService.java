package com.satyam.taskmanager.service;

import com.satyam.taskmanager.model.NoteModel;
import com.satyam.taskmanager.model.TaskModel;
import com.satyam.taskmanager.repository.NoteRepository;
import com.satyam.taskmanager.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    NoteRepository noteRepository;

    public void addTask(TaskModel taskModel){
        taskRepository.save(taskModel);
    }

    public List<TaskModel> getAllTasks(Boolean isCompleted){
        if(isCompleted == null){
            return taskRepository.findAll();
        }
        return taskRepository.findByIsCompleted(isCompleted);
    }

    public TaskModel getTaskById(long id){
        Optional<TaskModel> taskOptional = taskRepository.findById(id);
        if(taskOptional.isEmpty()){
            throw  new RuntimeException("Task not found");
        }
        return taskOptional.get();
    }
    public void updateTask(TaskModel task) {
        Optional<TaskModel> taskModelOptional = taskRepository.findById(task.getId());
        if(taskModelOptional.isEmpty()){
            throw new RuntimeException("Task not found");
        }
        TaskModel existingTask = taskModelOptional.get();
        modelMapper.map(task,existingTask);
        taskRepository.save(existingTask);
    }

    public void addNote(long id, NoteModel note){
        Optional<TaskModel> task = taskRepository.findById(id);
        TaskModel taskModel = task.get();
        if(taskModel == null){
            throw new RuntimeException("Task not found");
        }
        note.setTask(taskModel);
        noteRepository.save(note);
    }

    public void deleteTaskById(long id){
        taskRepository.deleteById(id);
    }

    public List<NoteModel> getTaskNotesByTaskId(Long taskId){
        Optional<TaskModel> task = taskRepository.findById(taskId);
        if(task.isEmpty()){
            throw new RuntimeException();
        }
        return task.get().getNotes();
    }

    public void deleteNoteById(long taskId, long noteId) {
        Optional<TaskModel> taskModel = taskRepository.findById(taskId);
        if(taskModel.isEmpty()){
            throw new RuntimeException();
        }
        boolean ifNotePresent = taskModel.get().getNotes().stream().anyMatch(t -> t.getId() == noteId);
        if(ifNotePresent){
            noteRepository.deleteById(noteId);
        }
    }
}
