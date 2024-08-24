package com.example.test_task_it_power.model.dto;

import com.example.test_task_it_power.dao.entity.TaskEntity;

import java.time.OffsetDateTime;

public record Task(
        Long id,
        String title,
        OffsetDateTime creationTime,
        String description
) {

    public static Task of(TaskEntity taskEntity) {
        return new Task(taskEntity.getTaskId(), taskEntity.getTitle(), OffsetDateTime.now(), taskEntity.getDescription());
    }
}
