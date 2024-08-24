package com.example.test_task_it_power.model.dto.response;

import com.example.test_task_it_power.model.dto.Task;

import java.time.OffsetDateTime;

public record TaskResponse(
        Long id,
        String title,
        OffsetDateTime creationTime,
        String description
) {

    public static TaskResponse of(Task task) {
        return new TaskResponse(task.id(), task.title(), task.creationTime(), task.description());
    }
}
