package com.github.cuidok.oa.server.task;

import com.github.cuidok.oa.server.task.mapper.TaskQueryMapper;
import com.github.cuidok.oa.server.task.model.Task;
import com.github.cuidok.oa.server.task.model.TaskQueryParam;
import com.github.cuidok.oa.server.task.model.TaskSortField;
import com.github.cuidok.oa.server.task.model.TaskSortParam;
import com.github.cuidok.oa.server.task.model.TaskSortType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TaskQueryService {

    private final TaskQueryMapper taskQueryMapper;

    public TaskQueryService(TaskQueryMapper taskQueryMapper) {
        this.taskQueryMapper = taskQueryMapper;
    }

    public List<Task> query(Integer userId, TaskQueryParam queryParam, TaskSortParam sortParam) {

        // Check input parameters
        if (userId == null) {
            log.warn("User ID must not be null");
            throw new IllegalArgumentException("User ID must not be null");
        }

        // Init query parameters
        if (queryParam == null) {
            queryParam = new TaskQueryParam();
        }
        if (queryParam.getPageSize() == null) {
            queryParam.setPageSize(10);
        }
        if (queryParam.getPageNum() == null) {
            queryParam.setPageNum(1);
        }

        // Init sort parameters
        if (sortParam == null) {
            sortParam = new TaskSortParam();
        }
        if (sortParam.getField() == null) {
            sortParam.setField(TaskSortField.CREATE_TIME);
        }
        if (sortParam.getType() == null) {
            sortParam.setType(TaskSortType.DESC);
        }

        return taskQueryMapper.selectTasks(userId, queryParam, sortParam);
    }
}
