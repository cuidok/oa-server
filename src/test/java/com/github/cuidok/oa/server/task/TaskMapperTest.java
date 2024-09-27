package com.github.cuidok.oa.server.task;

import com.github.cuidok.oa.server.task.model.Task;
import com.github.cuidok.oa.server.task.model.TaskStatus;
import com.github.cuidok.oa.server.util.LocalDateTimeCompare;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTest {

    @Autowired
    private TaskInsertMapper taskInsertMapper;

    @Autowired
    private TaskQueryMapper taskQueryMapper;

    @Test
    public void testInsert() {

        Task task = new Task();
        task.setUserId(1);
        task.setTitle("TEST_" + System.currentTimeMillis());
        task.setContent("TEST_CONTENT");
        task.setStatus(TaskStatus.NotStarted);
        task.setStartTime(LocalDateTime.now());
        task.setEndTime(LocalDateTime.now().plusDays(1));
        task.setCompleteTime(LocalDateTime.now().plusDays(2));
        task.setCreateTime(LocalDateTime.now());
        task.setUpdateTime(LocalDateTime.now());

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
}