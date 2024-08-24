package com.example.test_task_it_power.service;

import com.example.test_task_it_power.dao.entity.Role;
import com.example.test_task_it_power.dao.entity.UserEntity;
import com.example.test_task_it_power.model.dto.User;
import com.example.test_task_it_power.model.dto.request.RegisterUserRequest;
import com.example.test_task_it_power.model.dto.request.SignInRequest;
import com.example.test_task_it_power.model.dto.response.JwtAuthenticationResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse registerUser(RegisterUserRequest request) {
        UserEntity user = UserEntity.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.ROLE_USER)
                .build();

        User registered = userService.registerUser(user);

        String jwt = jwtService.generateToken(user);
        log.info("JWT generated for user {}", registered.id());
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
        ));
        log.info("Authentication successful");
        UserDetails user = userService
                .userDetailsService()
                .loadUserByUsername(request.email());
        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
