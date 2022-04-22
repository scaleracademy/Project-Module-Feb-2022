

## Project Requirements

task manager + notes support

1. ability to add new tasks
   1.1 task has status (done/not done)
   1.2 by default due date  = today + 7 days, can be specified by user

2. ability to change tasks
   2.1 modify due date, done/not done status
   2.2 delete any task

3. ability to add notes to tasks
   3.1 view all notes within a task
   3.2 add new note to a particular task
   3.3 delete notes for a particular task

----
API Endpoints  (REST URLs)

GET   	/tasks 					get all tasks               ✅
GET   	/tasks/{id}	    		get a task by id            ✅
DELETE	/tasks/{id}				delete task by id           ✅
PATCH	/tasks/{id}				update details of a task    ✅
POST  	/tasks					create a new task           ✅

GET   	/tasks/{id}/notes     	show all notes of a task    ✅
POST  	/tasks/{id}/notes 		add notes to a task         ✅
DELETE	/tasks/{id}/notes/{nid} delete a note from a task   ✅

----     "idempotent"

DB entities ?

1. tasks
    - id 			int, primarykey
    - name			string
    - due_date		date
    - status		boolean

2. notes
    - id			int, primarykey
    - body			string
    - task_id		foreignkey(tasks.id)

------ 