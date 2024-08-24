package com.example.test_task_it_power.controller;

import com.example.test_task_it_power.model.dto.Task;
import com.example.test_task_it_power.model.dto.request.AddTaskRequest;
import com.example.test_task_it_power.model.dto.request.PatchTaskRequest;
import com.example.test_task_it_power.model.dto.request.RemoveTaskRequest;
import com.example.test_task_it_power.model.dto.response.TaskResponse;
import com.example.test_task_it_power.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/{id}/tasks")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TaskResponse> addTask(@RequestBody @Valid AddTaskRequest addTaskRequest, @PathVariable("id") Long userId) {
        Task task = taskService.addTask(addTaskRequest, userId);

        return new ResponseEntity<>(TaskResponse.of(task), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/tasks")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TaskResponse> removeTask(@RequestBody @Valid RemoveTaskRequest removeTaskRequest, @PathVariable("id") Long userId) {
        Task task = taskService.removeTask(removeTaskRequest, userId);

        return new ResponseEntity<>(TaskResponse.of(task), HttpStatus.OK);
    }

    @PostMapping("/{id}/tasks/{taskId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TaskResponse> delegateTask(@PathVariable("id") Long idFrom, @RequestHeader("userId") Long idTo, @PathVariable("taskId") Long taskId) {
        Task task = taskService.delegateTask(idFrom, idTo, taskId);

        return new ResponseEntity<>(TaskResponse.of(task), HttpStatus.OK);
    }

    @PatchMapping("/{id}/tasks/{taskId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TaskResponse> patchTask(@PathVariable("id") Long userId, @PathVariable("taskId") Long taskId, @RequestBody Map<String, Object> fields) {
        Task task = taskService.patchTask(userId, taskId, fields);
        return new ResponseEntity<>(TaskResponse.of(task), HttpStatus.OK);
    }

    @GetMapping("/{id}/tasks")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Page<Task>> getTasks(@PathVariable("id") Long userId, @PathParam("page") @NotNull Integer page, @PathParam("size") @NotNull Integer size) {
        Page<Task> tasks = taskService.getTasks(page, size, userId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

}
