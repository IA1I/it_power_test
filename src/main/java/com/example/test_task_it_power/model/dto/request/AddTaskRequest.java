package com.example.test_task_it_power.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public record AddTaskRequest(
        @NotBlank(message = "Title is not valid")
        String title,
        @NotNull
        OffsetDateTime createdAt,
        @NotNull
        String description
) {
}
