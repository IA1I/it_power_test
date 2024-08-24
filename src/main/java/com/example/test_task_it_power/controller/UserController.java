package com.example.test_task_it_power.controller;

import com.example.test_task_it_power.model.dto.User;
import com.example.test_task_it_power.model.dto.response.ListUsersResponse;
import com.example.test_task_it_power.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ListUsersResponse> getAllUsers() {
        List<User> users = userService.getAllUsers();

        return new ResponseEntity<>(ListUsersResponse.of(users), HttpStatus.OK);
    }
}
