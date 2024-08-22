package com.example.test_task_it_power.dao.jpa;

import com.example.test_task_it_power.dao.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTaskRepository extends JpaRepository<TaskEntity, Long> {
}
