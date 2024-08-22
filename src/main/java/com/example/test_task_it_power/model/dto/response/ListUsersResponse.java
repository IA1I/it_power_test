package com.example.test_task_it_power.model.dto.response;

import java.util.List;

public record ListUsersResponse(
        List<UserResponse> users,
        Integer size
) {
}
