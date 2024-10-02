package com.github.cuidok.oa.server.task;

import com.github.cuidok.oa.server.task.mapper.TaskDeleteMapper;
import com.github.cuidok.oa.server.task.mapper.TaskInsertMapper;
import com.github.cuidok.oa.server.task.mapper.TaskQueryMapper;
import com.github.cuidok.oa.server.task.model.Task;
import com.github.cuidok.oa.server.util.LocalDateTimeCompare;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTest {

    @Autowired
    private TaskInsertMapper taskInsertMapper;

    @Autowired
    private TaskQueryMapper taskQueryMapper;

    @Autowired
    private TaskDeleteMapper taskDeleteMapper;

    @Test
    public void testInsert() {

        Task task = TaskBuilder.buildComplete();

        taskInsertMapper.insertTask(task);

        Task taskFromDatabase = taskQueryMapper.selectTaskById(task.getId());
        LocalDateTimeCompare compare = new LocalDateTimeCompare();

        assertNotNull(taskFromDatabase);
        assertEquals(task.getUserId(), taskFromDatabase.getUserId());
        assertEquals(task.getTitle(), taskFromDatabase.getTitle());
        assertEquals(task.getContent(), taskFromDatabase.getContent());
        assertEquals(task.getStatus(), taskFromDatabase.getStatus());
        assertTrue(compare.compareTimeDifference(task.getStartTime(), taskFromDatabase.getStartTime(), 2));
        assertTrue(compare.compareTimeDifference(task.getEndTime(), taskFromDatabase.getEndTime(), 2));
        assertTrue(compare.compareTimeDifference(task.getCompleteTime(), taskFromDatabase.getCompleteTime(), 2));
        assertTrue(compare.compareTimeDifference(task.getCreateTime(), taskFromDatabase.getCreateTime(), 2));
        assertTrue(compare.compareTimeDifference(task.getUpdateTime(), taskFromDatabase.getUpdateTime(), 2));
    }

    @Test
    public void testDelete() {

        Task task = TaskBuilder.buildComplete();

        taskInsertMapper.insertTask(task);

        Task taskFromDatabase = taskQueryMapper.selectTaskById(task.getId());
        assertNotNull(taskFromDatabase);

        taskDeleteMapper.deleteTask(task.getId());

        Task taskFromDatabaseAfterDelete = taskQueryMapper.selectTaskById(task.getId());
        assertNull(taskFromDatabaseAfterDelete);
    }
}