package com.github.cuidok.oa.server.task.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskCreateParam {

    private String title;

    private String content;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}
