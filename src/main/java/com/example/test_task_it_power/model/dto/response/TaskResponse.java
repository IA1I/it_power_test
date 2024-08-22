package com.example.test_task_it_power.model.dto.response;

import java.time.OffsetDateTime;

public record TaskResponse(
        Long id,
        String title,
        OffsetDateTime creationTime,
        String description
) {
}
