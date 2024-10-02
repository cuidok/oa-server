package com.github.cuidok.oa.server.task;

import com.github.cuidok.oa.server.exception.NoPermissionException;
import com.github.cuidok.oa.server.task.mapper.TaskQueryMapper;
import com.github.cuidok.oa.server.task.mapper.TaskUpdateMapper;
import com.github.cuidok.oa.server.task.model.Task;
import com.github.cuidok.oa.server.task.model.TaskStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class TaskModifyService {

    private final TaskQueryMapper taskQueryMapper;

    private final TaskUpdateMapper taskUpdateMapper;

    private final TaskPermissionChecker taskPermissionChecker;

    @Autowired
    public TaskModifyService(TaskQueryMapper taskQueryMapper, TaskUpdateMapper taskUpdateMapper) {
        this.taskQueryMapper = taskQueryMapper;
        this.taskUpdateMapper = taskUpdateMapper;
        taskPermissionChecker = TaskPermissionChecker.getInstance();
    }

    public void updateTaskStatus(Integer userId, Integer taskId, TaskStatus status) {

        // Check input parameters
        if (userId == null || taskId == null || status == null) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        // Check user permission
        Task taskFromDB = taskQueryMapper.selectTaskById(taskId);
        if (!taskPermissionChecker.hasPermission(taskFromDB, userId)) {
            log.info("User {} does not have permission to modify task {}", userId, taskId);
            throw new NoPermissionException("User does not have permission to modify this task");
        }

        // Modify the task status
        taskUpdateMapper.updateTaskStatus(taskId, status, LocalDateTime.now());
    }
}