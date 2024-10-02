package com.github.cuidok.oa.server.task;

import com.github.cuidok.oa.server.task.mapper.TaskInsertMapper;
import com.github.cuidok.oa.server.task.mapper.TaskQueryMapper;
import com.github.cuidok.oa.server.task.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskDeleteServiceTest {

    @Autowired
    private TaskInsertMapper taskInsertMapper;

    @Autowired
    private TaskQueryMapper taskQueryMapper;

    @Autowired
    private TaskDeleteService taskDeleteService;

    @Test
    void deleteTask() {

        Task task = TaskBuilder.buildComplete();

        taskInsertMapper.insertTask(task);

        Task taskFromDatabase = taskQueryMapper.selectTaskById(task.getId());
        assertNotNull(taskFromDatabase);

        taskDeleteService.deleteTask(task.getUserId(), task.getId());

        Task taskFromDatabaseAfterDelete = taskQueryMapper.selectTaskById(task.getId());
        assertNull(taskFromDatabaseAfterDelete);
    }
}