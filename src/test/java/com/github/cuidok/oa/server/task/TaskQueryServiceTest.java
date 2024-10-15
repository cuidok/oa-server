package com.github.cuidok.oa.server.task;

import com.github.cuidok.oa.server.task.mapper.TaskInsertMapper;
import com.github.cuidok.oa.server.task.model.Task;
import com.github.cuidok.oa.server.task.model.TaskQueryParam;
import com.github.cuidok.oa.server.task.model.TaskSortField;
import com.github.cuidok.oa.server.task.model.TaskSortParam;
import com.github.cuidok.oa.server.task.model.TaskSortType;
import com.github.cuidok.oa.server.task.model.TaskStatus;
import com.github.cuidok.oa.server.util.LocalDateTimeCompare;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskQueryServiceTest {

    @Autowired
    private TaskQueryService taskQueryService;

    @Autowired
    private TaskInsertMapper taskInsertMapper;

    @Test
    @Transactional
    void query() {

        Task task1 = TaskBuilder.buildComplete();
        taskInsertMapper.insertTask(task1);
        Task task2 = TaskBuilder.buildComplete();
        taskInsertMapper.insertTask(task2);

        TaskQueryParam taskQueryParam = new TaskQueryParam();
        taskQueryParam.setTaskStatus(TaskStatus.NotStarted);

        task1.setStatus(taskQueryParam.getTaskStatus());
        task2.setStatus(taskQueryParam.getTaskStatus());

        TaskSortParam taskSortParam = new TaskSortParam();
        taskSortParam.setField(TaskSortField.CREATE_TIME);
        taskSortParam.setType(TaskSortType.ASC);

        List<Task> tasks = taskQueryService.query(task1.getUserId(), taskQueryParam, taskSortParam);
        LocalDateTimeCompare localDateTimeCompare = new LocalDateTimeCompare();
        assertNotNull(tasks);
        assertTrue(tasks.size() >= 2);
        for (Task task : tasks) {
            System.out.println(task);
            assertEquals(task.getStatus(), taskQueryParam.getTaskStatus());
            if (task.getId().equals(task1)) {
                assertEquals(task1.getTitle(), task.getTitle());
                assertEquals(task1.getContent(), task.getContent());
                assertEquals(task1.getStatus(), task.getStatus());
                assertTrue(localDateTimeCompare.compareTimeDifference(task1.getStartTime(), task.getStartTime(), 2));
                assertTrue(localDateTimeCompare.compareTimeDifference(task1.getEndTime(), task.getEndTime(), 2));
                assertTrue(localDateTimeCompare.compareTimeDifference(task1.getCompleteTime(), task.getCompleteTime(), 2));
                assertTrue(localDateTimeCompare.compareTimeDifference(task1.getCreateTime(), task.getCreateTime(), 2));
                assertTrue(localDateTimeCompare.compareTimeDifference(task1.getUpdateTime(), task.getUpdateTime(), 2));
            } else if (task.getId().equals(task2)) {
                assertEquals(task2.getTitle(), task.getTitle());
                assertEquals(task2.getContent(), task.getContent());
                assertEquals(task2.getStatus(), task.getStatus());
                assertTrue(localDateTimeCompare.compareTimeDifference(task2.getStartTime(), task.getStartTime(), 2));
                assertTrue(localDateTimeCompare.compareTimeDifference(task2.getEndTime(), task.getEndTime(), 2));
                assertTrue(localDateTimeCompare.compareTimeDifference(task2.getCompleteTime(), task.getCompleteTime(), 2));
                assertTrue(localDateTimeCompare.compareTimeDifference(task2.getCreateTime(), task.getCreateTime(), 2));
                assertTrue(localDateTimeCompare.compareTimeDifference(task2.getUpdateTime(), task.getUpdateTime(), 2));
            }
        }
    }
}