package com.example.test_task_it_power.model.dto.response;

public record UserResponse(
        Long id,
        String email,
        String password,
        String nick
) {
}
