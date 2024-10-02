package com.github.cuidok.oa.server.task;

import com.github.cuidok.oa.server.task.mapper.TaskInsertMapper;
import com.github.cuidok.oa.server.task.mapper.TaskQueryMapper;
import com.github.cuidok.oa.server.task.model.Task;
import com.github.cuidok.oa.server.task.model.TaskStatus;
import com.github.cuidok.oa.server.util.LocalDateTimeCompare;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskModifyServiceTest {

    @Autowired
    private TaskModifyService taskModifyService;

    @Autowired
    private TaskQueryMapper taskQueryMapper;

    @Autowired
    private TaskInsertMapper taskInsertMapper;

    @Test
    void updateTaskStatus() {

        Task task = TaskBuilder.buildComplete();
        taskInsertMapper.insertTask(task);

        Task taskFromDatabase = taskQueryMapper.selectTaskById(task.getId());
        assertNotNull(taskFromDatabase);

        taskModifyService.updateTaskStatus(task.getUserId(), task.getId(), TaskStatus.InProgress);

        Task taskFromDatabaseAfterUpdate = taskQueryMapper.selectTaskById(task.getId());
        LocalDateTimeCompare compare = new LocalDateTimeCompare();
        assertNotNull(taskFromDatabaseAfterUpdate);
        assertEquals(taskFromDatabaseAfterUpdate.getId(), taskFromDatabase.getId());
        assertEquals(taskFromDatabaseAfterUpdate.getUserId(), taskFromDatabase.getUserId());
        assertEquals(taskFromDatabaseAfterUpdate.getTitle(), taskFromDatabase.getTitle());
        assertEquals(taskFromDatabaseAfterUpdate.getContent(), taskFromDatabase.getContent());
        assertEquals(taskFromDatabaseAfterUpdate.getStatus(), TaskStatus.InProgress);
        assertEquals(taskFromDatabaseAfterUpdate.getStartTime(), taskFromDatabase.getStartTime());
        assertEquals(taskFromDatabaseAfterUpdate.getEndTime(), taskFromDatabase.getEndTime());
        assertEquals(taskFromDatabaseAfterUpdate.getCompleteTime(), taskFromDatabase.getCompleteTime());
        assertEquals(taskFromDatabaseAfterUpdate.getCreateTime(), taskFromDatabase.getCreateTime());
        assertTrue(compare.compareTimeDifference(taskFromDatabaseAfterUpdate.getUpdateTime(), taskFromDatabase.getUpdateTime(), 2));
    }

    @Test
    void finishTask() {

        Task task = TaskBuilder.buildComplete();
        taskInsertMapper.insertTask(task);
    }
}