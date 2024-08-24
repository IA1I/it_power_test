package com.example.test_task_it_power.service.jpa;

import com.example.test_task_it_power.dao.entity.UserEntity;
import com.example.test_task_it_power.dao.jpa.JpaUserRepository;
import com.example.test_task_it_power.exception.EmailAlreadyExistsException;
import com.example.test_task_it_power.exception.UserNotFoundException;
import com.example.test_task_it_power.model.dto.User;
import com.example.test_task_it_power.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class JpaUserService implements UserService {
    private static final String USER_NOT_FOUND = "User not found";
    private static final String EMAIL_ALREADY_EXISTS = "User with this email already exists";
    private JpaUserRepository jpaUserRepository;

    @Override
    @Transactional
    public User registerUser(UserEntity userEntity) {
        if (jpaUserRepository.existsByEmail(userEntity.getEmail())) {
            log.info("Email {} already exists", userEntity.getEmail());
            throw new EmailAlreadyExistsException(EMAIL_ALREADY_EXISTS);
        }

        UserEntity save = jpaUserRepository.save(userEntity);
        log.info("Registered user {}", save);
        return User.of(save);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        List<UserEntity> all = jpaUserRepository.findAll();
        List<User> users = new ArrayList<>();
        for (UserEntity userEntity : all) {
            users.add(User.of(userEntity));
        }
        log.info("Get all {} users", users.size());
        return users;
    }

    @Transactional
    public UserEntity getByUsername(String email) {
        return jpaUserRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }
}
