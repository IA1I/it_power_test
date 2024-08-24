package com.example.test_task_it_power.dao.jpa;

import com.example.test_task_it_power.dao.entity.TaskEntity;
import com.example.test_task_it_power.dao.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTaskRepository extends JpaRepository<TaskEntity, Long> {
    boolean existsByTitle(String title);

    Optional<TaskEntity> findByTitle(String title);

    Page<TaskEntity> findAllByUser(UserEntity user, Pageable pageable);
}
