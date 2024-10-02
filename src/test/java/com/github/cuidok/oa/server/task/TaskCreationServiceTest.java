package com.github.cuidok.oa.server.task;

import com.github.cuidok.oa.server.task.mapper.TaskQueryMapper;
import com.github.cuidok.oa.server.task.model.Task;
import com.github.cuidok.oa.server.task.model.TaskCreateParam;
import com.github.cuidok.oa.server.task.model.TaskStatus;
import com.github.cuidok.oa.server.util.LocalDateTimeCompare;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskCreationServiceTest {

    @Autowired
    private TaskCreationService taskCreationService;

    @Autowired
    private TaskQueryMapper taskQueryMapper;

    @Test
    void createTask() {

        Integer userId = 1;
        TaskCreateParam param = new TaskCreateParam();
        param.setTitle("test_title");
        param.setContent("test_content");


        Task task = taskCreationService.createTask(userId, param);

        Task taskFromDb = taskQueryMapper.selectTaskById(task.getId());
        LocalDateTimeCompare compare = new LocalDateTimeCompare();
        assertNotNull(taskFromDb);
        assertEquals(userId, taskFromDb.getUserId());
        assertEquals(param.getTitle(), taskFromDb.getTitle());
        assertEquals(param.getContent(), taskFromDb.getContent());
        assertEquals(TaskStatus.NotStarted, taskFromDb.getStatus());
        assertNotNull(taskFromDb.getStartTime());
        assertNotNull(taskFromDb.getEndTime());
        assertNotNull(taskFromDb.getCreateTime());
        assertNotNull(taskFromDb.getUpdateTime());
        assertTrue(compare.compareTimeDifference(task.getStartTime(), taskFromDb.getStartTime(), 2));
        assertTrue(compare.compareTimeDifference(task.getEndTime(), taskFromDb.getEndTime(), 2));
        assertTrue(compare.compareTimeDifference(task.getCreateTime(), taskFromDb.getCreateTime(), 2));
        assertTrue(compare.compareTimeDifference(task.getUpdateTime(), taskFromDb.getUpdateTime(), 2));
    }
}