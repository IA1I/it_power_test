package com.example.test_task_it_power.service;

import com.example.test_task_it_power.model.dto.User;
import com.example.test_task_it_power.model.dto.request.RegisterUserRequest;

import java.util.List;

public interface UserService {
    User registeruser(RegisterUserRequest registerUserRequest);

    List<User> getAllUsers();
}
