package com.github.cuidok.oa.server.task.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskQueryParam {

    private TaskStatus taskStatus;

    private LocalDateTime createTimeFrom;

    private LocalDateTime createTimeTo;

    private LocalDateTime startTimeFrom;

    private LocalDateTime startTimeTo;

    private LocalDateTime endTimeFrom;

    private LocalDateTime endTimeTo;

    private LocalDateTime updateTimeFrom;

    private LocalDateTime updateTimeTo;

    private String title;

    private String content;

    private Integer pageNum;

    private Integer pageSize;

}
