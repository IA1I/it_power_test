package com.example.test_task_it_power.model.dto.response;

import com.example.test_task_it_power.model.dto.User;

import java.util.ArrayList;
import java.util.List;

public record ListUsersResponse(
        List<UserResponse> users,
        Integer size
) {

    public static ListUsersResponse of(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(UserResponse.of(user));
        }

        return new ListUsersResponse(userResponses, userResponses.size());
    }
}
