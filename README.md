# TASK MANAGER

## Desciption
A web application for managing personal tasks and notes within a given task.

---

## Requirements
- CRUD capabilities for tasks and for **NOTES** within a **TASK**. - *Notes can be considered as descriptors to that task*
- A **TASK** should be able to have multiple **NOTES** linked to it. Initially, a note can only be linked to one task. - *i.e. a TASK has many NOTES*

## JSON Entities

### Task
    {
	    "id": 31,
	    "title": "I am a task!",
	    "description": "This is the task description",
	    "deadline": "dd/mm/yyyy",
      "notes": [
        {
          "title": "random note title",
          "body": "I am the note body"
        },
        {
          "title": "random note title",
          "body": "I am the note body"
        },
        {
          "title": "random note title",
          "body": "I am the note body"
        }
      ],
      "completed": false
    }

## API Endpoints

### `POST /tasks`
Create a new task

### `GET /tasks`
Get all tasks
Available filters -
- `/tasks?completed=true/false`

### `GET /tasks/{task_id}`
Get the details of a particular task including notes

### `PATCH /tasks/{task_id}`
Edit a task - Add / Remove notes from the task. Mark a task completed.

### `PATCH /tasks/{task_id}`

### `DELETE /tasks/{task_id}`
Delete a particular task

### `GET  /tasks/{task_id}/notes`
Fetch all the notes under a particular task

### `POST  /tasks/{task_id}/notes`
Create a new note under the task with given task id

### `DELETE /tasks/{task_id}/notes/{notes_id}`
Delete a note
