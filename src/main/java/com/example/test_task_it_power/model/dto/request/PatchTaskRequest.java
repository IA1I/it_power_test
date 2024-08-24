package com.example.test_task_it_power.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PatchTaskRequest(
        @Pattern(regexp = "replace")
        String op,
        @NotBlank
        String path,
        @NotNull
        Object value
) {
}
