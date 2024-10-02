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

1. Accept the user id and the parameters used to create the task.
2. Check input parameters.
3. Create the task.
4. Init the task's parameters.
5. Return the task id.

The task status is `NotStarted` when the task is created.

The creation time and update time of the task are the current time.
The update time is the same as the creation time when the task is created.

### Modify the task status

The function modifies the task status, the function follows the steps below:

1. Accept the user id, task id and new status.
2. Check input parameters.
3. Check the user permission.
4. Modify the task status.
5. Update the task's update time.

### Finish the task

The function finishes the task, the function follows the steps below:

1. Accept the user id and task id.
2. Check input parameters.
3. Check the user permission.
4. Finish the task.
5. Update the task's update time and complete time.

The task status is `Completed` when the task is finished.

### Delete the task

The function deletes the task, the function follows the steps below:

1. Accept the user id and task id.
2. Check input parameters.
3. Check the user permission.
4. Delete the task.

When we delete the task, we will physically delete the task from the database.

### Query the task

The system support querying using a combination of task status, create time, start time, end time, update time,
and complete time.

The system uses time as a range query, so all times have a 'from time' and a 'to time'.

If the 'from time' is NULL, the system will query the task which is earlier than the 'to time'.

If the 'to time' is NULL, the system will query the task which is later than the 'from time'.

If the 'from time' equals the 'to time', the system will query the task in the same day.

The system support fuzzy querying using the task title.

The system support querying using the task content.

If the task status, create time, update time, complete time, task title, and task content are all empty, the system 
will not use these parameters for querying.

The system only supports sorting by one parameter among create time, start time, end time, update time, and complete time.

By default, we use create time for descending order sorting.

The page size can be one of 10, 50, 100, NULL, if the page size is NULL, the system will query all the data.

the default page size is 10.

The page number starts from 1, and the default page number is 1.

The function follows the steps below:

1. Accept the user id and query parameters.
2. Check input parameters.
3. Query the task.