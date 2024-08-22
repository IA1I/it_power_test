package com.example.test_task_it_power.model.dto;

import java.time.OffsetDateTime;

public record Task(
        Long id,
        String title,
        OffsetDateTime creationTime,
        String description
) {
}
