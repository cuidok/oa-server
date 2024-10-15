package com.github.cuidok.oa.server.task.model;

import lombok.Data;

@Data
public class TaskSortParam {

    private TaskSortField field;

    private TaskSortType type;
}
