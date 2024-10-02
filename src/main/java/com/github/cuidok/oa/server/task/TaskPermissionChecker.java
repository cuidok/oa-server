package com.github.cuidok.oa.server.task;

import com.github.cuidok.oa.server.task.model.Task;

public class TaskPermissionChecker {

    private static TaskPermissionChecker instance;

    public static synchronized TaskPermissionChecker getInstance() {
        if (instance == null) {
            return new TaskPermissionChecker();
        }
        return instance;
    }

    public boolean hasPermission(Task task, Integer inputUserId) {
        return inputUserId.equals(task.getUserId());
    }

}
