package com.github.cuidok.oa.server.task;

import com.github.cuidok.oa.server.task.model.Task;
import com.github.cuidok.oa.server.task.model.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskCreationService {

    private final TaskInsertMapper taskInsertMapper;

    @Autowired
    public TaskCreationService(TaskInsertMapper taskInsertMapper) {
        this.taskInsertMapper = taskInsertMapper;
    }

    public Task createTask(Integer userId, String title, String content, LocalDateTime startTime, LocalDateTime endTime) {

        // Check input parameters
        if (userId == null || title == null || content == null) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        LocalDateTime currentTime = LocalDateTime.now();

        if (startTime == null) {
            startTime = currentTime;
        }

        if (endTime == null) {
            endTime = currentTime.plusDays(1);
        }

        // Create the task
        Task task = new Task();
        task.setUserId(userId);
        task.setTitle(title);
        task.setContent(content);
        task.setStatus(TaskStatus.NotStarted);
        task.setStartTime(startTime);
        task.setEndTime(endTime);
        task.setCreateTime(currentTime);
        task.setUpdateTime(currentTime);

        // Insert the task and return the generated id
        taskInsertMapper.insertTask(task);
        return task;
    }
}