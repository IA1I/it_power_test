package com.example.test_task_it_power.service;

import com.example.test_task_it_power.dao.entity.UserEntity;
import com.example.test_task_it_power.model.dto.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    User registerUser(UserEntity UserEntity);

    List<User> getAllUsers();

    UserDetailsService userDetailsService();
}
