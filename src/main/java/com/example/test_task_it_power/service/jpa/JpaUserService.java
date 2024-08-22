package com.example.test_task_it_power.service.jpa;

import com.example.test_task_it_power.model.dto.User;
import com.example.test_task_it_power.model.dto.request.RegisterUserRequest;
import com.example.test_task_it_power.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaUserService implements UserService {

    @Override
    public User registeruser(RegisterUserRequest registerUserRequest) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }
}
