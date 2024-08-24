package com.example.test_task_it_power.service.jpa;

import com.example.test_task_it_power.dao.entity.TaskEntity;
import com.example.test_task_it_power.dao.entity.UserEntity;
import com.example.test_task_it_power.dao.jpa.JpaTaskRepository;
import com.example.test_task_it_power.dao.jpa.JpaUserRepository;
import com.example.test_task_it_power.exception.TaskNotFoundException;
import com.example.test_task_it_power.exception.TitleAlreadyExistsException;
import com.example.test_task_it_power.exception.UserNotFoundException;
import com.example.test_task_it_power.model.dto.Task;
import com.example.test_task_it_power.model.dto.request.AddTaskRequest;
import com.example.test_task_it_power.model.dto.request.RemoveTaskRequest;
import com.example.test_task_it_power.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class JpaTaskService implements TaskService {
    private static final String USER_NOT_FOUND = "User not found";
    private static final String TITLE_ALREADY_EXISTS = "Title already exists";
    private static final String TASK_NOT_FOUND = "Task not found";
    private JpaTaskRepository taskRepository;
    private JpaUserRepository userRepository;

    @Override
    @Transactional
    public Task addTask(AddTaskRequest addTaskRequest, Long userId) {
        if (taskRepository.existsByTitle(addTaskRequest.title())) {
            log.info("Title {} already exists", addTaskRequest.title());
            throw new TitleAlreadyExistsException(TITLE_ALREADY_EXISTS);
        }
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        TaskEntity taskEntity = TaskEntity.builder()
                .title(addTaskRequest.title())
                .createdAt(addTaskRequest.createdAt())
                .description(addTaskRequest.description())
                .build();

        userEntity.addTask(taskEntity);
        taskRepository.save(taskEntity);
        log.info("Task {} added to user {}", addTaskRequest.title(), userId);
        return Task.of(taskEntity);
    }

    @Override
    @Transactional
    public Task removeTask(RemoveTaskRequest removeTaskRequest, Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        TaskEntity taskEntity = taskRepository.findByTitle(removeTaskRequest.title()).orElseThrow(() -> new TaskNotFoundException(TASK_NOT_FOUND));

        userEntity.removeTask(taskEntity);
        taskRepository.save(taskEntity);
        log.info("Task {} removed from user {}", removeTaskRequest.title(), userId);
        return Task.of(taskEntity);
    }

    @Override
    @Transactional
    public Task delegateTask(Long fromId, Long toId, Long taskId) {
        UserEntity fromUser = userRepository.findById(fromId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        UserEntity toUser = userRepository.findById(toId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        TaskEntity taskEntity = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(TASK_NOT_FOUND));

        fromUser.removeTask(taskEntity);
        toUser.addTask(taskEntity);
        taskRepository.save(taskEntity);
        log.info("Task {} delegated from {} to {}", taskId, fromId, toId);
        return Task.of(taskEntity);
    }

    @Override
    @Transactional
    public Task patchTask(Long userId, Long taskId, Map<String, Object> fields) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        TaskEntity taskEntity = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(TASK_NOT_FOUND));
        if (fields == null || fields.isEmpty()) {
            log.info("Empty fields");
            throw new RuntimeException("Empty fields");
        }

        fields.forEach((s, o) -> {
            Field field = ReflectionUtils.findField(Task.class, s);
            field.setAccessible(true);
            ReflectionUtils.setField(field, taskEntity, o);
        });

        taskRepository.save(taskEntity);
        log.info("Task {} updated", taskId);
        return Task.of(taskEntity);
    }

    @Override
    public Page<Task> getTasks(Integer page, Integer size, Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        Pageable pageable = PageRequest.of(page, size);
        Page<TaskEntity> all = taskRepository.findAllByUser(userEntity, pageable);
        log.info("Return task page {} size {} for user {}", all.getNumber(), all.getSize(), userId);
        return all.map(Task::of);
    }
}
