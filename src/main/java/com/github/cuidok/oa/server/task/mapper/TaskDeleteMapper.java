package com.github.cuidok.oa.server.task.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TaskDeleteMapper {

    void deleteTask(Integer taskId);
}
