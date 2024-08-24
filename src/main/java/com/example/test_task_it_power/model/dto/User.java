package com.example.test_task_it_power.model.dto;

import com.example.test_task_it_power.dao.entity.UserEntity;

public record User(
        Long id,
        String email,
        String password,
        String name
) {

    public static User of(UserEntity userEntity) {

        return new User(userEntity.getUserId(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getName());
    }
}
