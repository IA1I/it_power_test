package com.example.test_task_it_power.model.dto;

public record User(
        Long id,
        String email,
        String password,
        String nick
) {
}
