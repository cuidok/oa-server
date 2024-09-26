package com.github.cuidok.oa.server.task;

import com.github.cuidok.oa.server.task.model.Task;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TaskQueryMapper {

    Task selectTaskById(int id);
}
