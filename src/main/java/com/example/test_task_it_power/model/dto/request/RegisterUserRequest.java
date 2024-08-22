package com.example.test_task_it_power.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record RegisterUserRequest(
        @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
        @NotBlank(message = "Email cannot be blank")
        String email,
        @NotEmpty(message = "Password cannot be empty")
        String password,
        @NotEmpty(message = "Nick cannot be empty")
        String nick
) {
}
