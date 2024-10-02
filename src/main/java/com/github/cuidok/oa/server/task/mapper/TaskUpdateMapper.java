package com.github.cuidok.oa.server.task.mapper;

import com.github.cuidok.oa.server.task.model.TaskStatus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Mapper
@Repository
public interface TaskUpdateMapper {

    void updateTaskStatus(Integer taskId, TaskStatus status, LocalDateTime updateTime);
}
