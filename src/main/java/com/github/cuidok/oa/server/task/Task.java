package com.github.cuidok.oa.server.task;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Task {

    private Integer id;

    private Integer userId;

    private String title;

    private String content;

    private TaskStatus status;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}