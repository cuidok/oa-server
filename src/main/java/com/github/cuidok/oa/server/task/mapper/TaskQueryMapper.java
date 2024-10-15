package com.github.cuidok.oa.server.task.mapper;

import com.github.cuidok.oa.server.task.model.Task;
import com.github.cuidok.oa.server.task.model.TaskQueryParam;
import com.github.cuidok.oa.server.task.model.TaskSortParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskQueryMapper {

    Task selectTaskById(int id);

    List<Task> selectTasks(Integer userId, TaskQueryParam queryParam, TaskSortParam sortParam);
}
