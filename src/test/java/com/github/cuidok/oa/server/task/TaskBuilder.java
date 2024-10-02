package com.github.cuidok.oa.server.task;

import com.github.cuidok.oa.server.task.model.Task;
import com.github.cuidok.oa.server.task.model.TaskStatus;

import java.time.LocalDateTime;

public class TaskBuilder {

    public static Task buildComplete() {
        Task task = new Task();
        task.setUserId(1);
        task.setTitle("TEST_" + System.currentTimeMillis());
        task.setContent("TEST_CONTENT_" + System.currentTimeMillis());
        task.setStatus(TaskStatus.NotStarted);
        task.setStartTime(LocalDateTime.now());
        task.setEndTime(LocalDateTime.now().plusDays(1));
        task.setCompleteTime(LocalDateTime.now().plusDays(2));
        task.setCreateTime(LocalDateTime.now());
        task.setUpdateTime(LocalDateTime.now());
        return task;
    }

    public static Task buildUnfinished() {
        Task task = new Task();
        task.setUserId(1);
        task.setTitle("TEST_" + System.currentTimeMillis());
        task.setContent("TEST_CONTENT_" + System.currentTimeMillis());
        task.setStatus(TaskStatus.NotStarted);
        task.setStartTime(LocalDateTime.now());
        task.setEndTime(LocalDateTime.now().plusDays(1));
        task.setCreateTime(LocalDateTime.now());
        task.setUpdateTime(LocalDateTime.now());
        return task;
    }
}
