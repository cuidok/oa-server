package com.github.cuidok.oa.server.task;

import com.github.cuidok.oa.server.task.mapper.TaskInsertMapper;
import com.github.cuidok.oa.server.task.mapper.TaskQueryMapper;
import com.github.cuidok.oa.server.task.mapper.TaskUpdateMapper;
import com.github.cuidok.oa.server.task.model.Task;
import com.github.cuidok.oa.server.task.model.TaskStatus;
import com.github.cuidok.oa.server.util.LocalDateTimeCompare;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskUpdateMapperTest {

    @Autowired
    private TaskInsertMapper taskInsertMapper;

    @Autowired
    private TaskUpdateMapper taskUpdateMapper;

    @Autowired
    private TaskQueryMapper taskQueryMapper;

    @Test
    void updateTaskStatus() {

        Task task = TaskBuilder.buildComplete();
        taskInsertMapper.insertTask(task);

        Task taskFromDatabase = taskQueryMapper.selectTaskById(task.getId());

        LocalDateTime current = LocalDateTime.now();
        taskUpdateMapper.updateTaskStatus(task.getId(), TaskStatus.InProgress, current);

        Task newTask = taskQueryMapper.selectTaskById(task.getId());
        assertNotNull(newTask);
        assertEquals(taskFromDatabase.getUserId(), newTask.getUserId());
        assertEquals(taskFromDatabase.getTitle(), newTask.getTitle());
        assertEquals(taskFromDatabase.getContent(), newTask.getContent());
        assertEquals(TaskStatus.InProgress, newTask.getStatus());
        assertEquals(taskFromDatabase.getStartTime(), newTask.getStartTime());
        assertEquals(taskFromDatabase.getEndTime(), newTask.getEndTime());
        assertEquals(taskFromDatabase.getCompleteTime(), newTask.getCompleteTime());
        assertEquals(taskFromDatabase.getCreateTime(), newTask.getCreateTime());
        assertTrue(new LocalDateTimeCompare().compareTimeDifference(current, newTask.getUpdateTime(), 2));
    }
}