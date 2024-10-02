package com.github.cuidok.oa.server.task.mapper;

import com.github.cuidok.oa.server.task.model.Task;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TaskInsertMapper {

    Integer insertTask(Task task);
}
