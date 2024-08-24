package com.example.test_task_it_power.service;

import com.example.test_task_it_power.model.dto.Task;
import com.example.test_task_it_power.model.dto.request.AddTaskRequest;
import com.example.test_task_it_power.model.dto.request.PatchTaskRequest;
import com.example.test_task_it_power.model.dto.request.RemoveTaskRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface TaskService {
    Task addTask(AddTaskRequest addTaskRequest, Long userId);

    Task removeTask(RemoveTaskRequest removeTaskRequest, Long userId);

    Task delegateTask(Long fromId, Long toId, Long taskId);

    Task patchTask(Long userId, Long taskId, Map<String, Object> fields);

    Page<Task> getTasks(Integer page, Integer size, Long userId);
}
