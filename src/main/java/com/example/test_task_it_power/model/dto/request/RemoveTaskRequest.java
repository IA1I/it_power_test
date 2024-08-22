package com.example.test_task_it_power.model.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record RemoveTaskRequest(
        @NotEmpty(message = "Title cannot be empty")
        String title
) {
}
