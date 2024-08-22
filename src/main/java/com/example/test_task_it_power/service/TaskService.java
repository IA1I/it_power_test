package com.example.test_task_it_power.service;

import com.example.test_task_it_power.model.dto.Task;
import com.example.test_task_it_power.model.dto.request.AddTaskRequest;
import com.example.test_task_it_power.model.dto.request.RemoveTaskRequest;

public interface TaskService {
    Task addTask(AddTaskRequest addTaskRequest);

    Task removeTask(RemoveTaskRequest removeTaskRequest);
}
