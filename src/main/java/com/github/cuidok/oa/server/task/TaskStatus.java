package com.github.cuidok.oa.server.task;

public enum TaskStatus {
    NotStarted,  // The task is not started. This is the default status.
    InProgress,  // The task is in progress.
    Completed,   // The task is completed.
    Cancelled    // The task is cancelled.
}