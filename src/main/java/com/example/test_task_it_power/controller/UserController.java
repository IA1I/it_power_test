package com.example.test_task_it_power.controller;

import com.example.test_task_it_power.model.dto.User;
import com.example.test_task_it_power.model.dto.request.RegisterUserRequest;
import com.example.test_task_it_power.model.dto.response.ListUsersResponse;
import com.example.test_task_it_power.model.dto.response.UserResponse;
import com.example.test_task_it_power.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ListUsersResponse> getAllUsers() {
        List<User> users = userService.getAllUsers();

        return new ResponseEntity<>(ListUsersResponse.of(users), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        User user = userService.registeruser(registerUserRequest);

        return new ResponseEntity<>(UserResponse.of(user), HttpStatus.CREATED);
    }
}
