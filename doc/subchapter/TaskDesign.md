# Task design document

This is task module design document.

Task means "To-Do" in this system.

## Enum

### Task status

The task status is an enumeration type, the value is as follows:

- NotStarted: The task is not started, This is the default status.
- InProgress: The task is in progress.
- Completed: The task is completed.
- Cancelled: The task is cancelled.

## Function

### Create task

The function creates a task, the function follows the steps below:

1. Accept the user id, title, content, start time and end time.
2. Check input parameters.
3. Create the task.
4. Init the task's parameters.
5. Return the task id.

The task status is `NotStarted` when the task is created.

The creation time and update time of the task are the current time.
The update time is the same as the creation time when the task is created.
