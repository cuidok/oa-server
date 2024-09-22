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