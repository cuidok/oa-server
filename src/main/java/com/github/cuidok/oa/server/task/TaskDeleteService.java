package com.github.cuidok.oa.server.task;

import com.github.cuidok.oa.server.exception.NoPermissionException;
import com.github.cuidok.oa.server.task.mapper.TaskDeleteMapper;
import com.github.cuidok.oa.server.task.mapper.TaskQueryMapper;
import com.github.cuidok.oa.server.task.model.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TaskDeleteService {

    private final TaskDeleteMapper taskDeleteMapper;
    private final TaskQueryMapper taskQueryMapper;
    private final TaskPermissionChecker taskPermissionChecker;

    @Autowired
    public TaskDeleteService(TaskDeleteMapper taskDeleteMapper, TaskQueryMapper taskQueryMapper) {
        this.taskDeleteMapper = taskDeleteMapper;
        this.taskQueryMapper = taskQueryMapper;
        this.taskPermissionChecker = TaskPermissionChecker.getInstance();
    }

    public void deleteTask(Integer userId, Integer taskId) {

        // Check input parameters
        if (userId == null || taskId == null) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        // Check user permission
        Task taskFromDB = taskQueryMapper.selectTaskById(taskId);
        if (!taskPermissionChecker.hasPermission(taskFromDB, userId)) {
            log.info("User {} does not have permission to delete task {}", userId, taskId);
            throw new NoPermissionException("User does not have permission to delete this task");
        }

        // Delete the task
        taskDeleteMapper.deleteTask(taskId);
    }
}