package com.satyam.taskmanager.controller;

import com.satyam.taskmanager.model.NoteModel;
import com.satyam.taskmanager.model.TaskModel;
import com.satyam.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;


    @PostMapping("")
    public HttpStatus addTask(@RequestBody TaskModel task){
        taskService.addTask(task);
        return HttpStatus.CREATED;
    }

    @PatchMapping("")
    public ResponseEntity<String> updateTask(@RequestBody TaskModel task) {
        try{
            taskService.updateTask(task);
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<TaskModel>> getAllTasks(@RequestParam(required = false) Boolean isCompleted){
        List<TaskModel> tasks = taskService.getAllTasks(isCompleted);
        return new ResponseEntity<>(tasks,HttpStatus.OK);
    }

    @GetMapping("/{task_id}")
    public ResponseEntity<TaskModel> getTaskById(@PathVariable("task_id") long id){
        try {
            TaskModel task = taskService.getTaskById(id);
            return new ResponseEntity<>(task,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/notes")
    public ResponseEntity<String> addNoteToTask(@PathVariable("id") long id, @RequestBody NoteModel note){
        try {
            taskService.addNote(id, note);
            return new ResponseEntity<>("Note Added",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{task_id}/notes")
    public ResponseEntity<List<NoteModel>> getTaskNotesByTaskId(@PathVariable("task_id") long id){
        try{
            return new ResponseEntity<>(taskService.getTaskNotesByTaskId(id),HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{task_id}/notes/{note_id}")
    public ResponseEntity<List<NoteModel>> deleteNoteById(@PathVariable("task_id") long taskId, @PathVariable("note_id") long noteId){
        try{
            taskService.deleteNoteById(taskId, noteId);
            return  ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity deleteTaskById(long id){
        try {
            taskService.deleteTaskById(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
