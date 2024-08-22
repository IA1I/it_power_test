package com.example.test_task_it_power.model.dto.response;

import com.example.test_task_it_power.model.dto.User;

public record UserResponse(
        Long id,
        String email,
        String password,
        String nick
) {
    public static UserResponse of(User user) {
        return new UserResponse(user.id(), user.email(), user.password(), user.nick());
    }
}
