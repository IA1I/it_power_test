package com.example.test_task_it_power.controller;

import com.example.test_task_it_power.model.dto.request.RegisterUserRequest;
import com.example.test_task_it_power.model.dto.request.SignInRequest;
import com.example.test_task_it_power.model.dto.response.JwtAuthenticationResponse;
import com.example.test_task_it_power.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public ResponseEntity<JwtAuthenticationResponse> registerUser(@RequestBody @Valid RegisterUserRequest registerUserRequest) {
        JwtAuthenticationResponse response = authenticationService.registerUser(registerUserRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationResponse> signInUser(@RequestBody @Valid SignInRequest signInRequest) {
        JwtAuthenticationResponse response = authenticationService.signIn(signInRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
