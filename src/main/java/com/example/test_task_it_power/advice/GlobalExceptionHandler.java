package com.example.test_task_it_power.advice;

import com.example.test_task_it_power.exception.EmailAlreadyExistsException;
import com.example.test_task_it_power.exception.TaskNotFoundException;
import com.example.test_task_it_power.exception.TitleAlreadyExistsException;
import com.example.test_task_it_power.exception.UserNotFoundException;
import com.example.test_task_it_power.model.dto.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String SOMETHING_WENT_WRONG = "Something went wrong";
    private static final String INVALID_REQUEST_PARAMETERS = "Invalid request parameters";
    private static final String USER_NOT_FIND = "User not found";
    private static final String EMAIL_ALREADY_EXISTS = "Email already exists";
    private static final String TITLE_ALREADY_EXISTS = "Title already exists";
    private static final String TASK_NOT_FIND = "Task not found";

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiErrorResponse handleException(Exception exception) {
        return getApiErrorResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR, SOMETHING_WENT_WRONG);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class,
            MissingRequestHeaderException.class})
    public ApiErrorResponse handleMessageException(Exception exception) {
        return getApiErrorResponse(exception, HttpStatus.BAD_REQUEST, INVALID_REQUEST_PARAMETERS);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(UserNotFoundException.class)
    public ApiErrorResponse handleUserNotFoundException(UserNotFoundException exception) {
        return getApiErrorResponse(exception, HttpStatus.CONFLICT, USER_NOT_FIND);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ApiErrorResponse handleEmailAlreadyExistsException(EmailAlreadyExistsException exception) {
        return getApiErrorResponse(exception, HttpStatus.CONFLICT, EMAIL_ALREADY_EXISTS);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(TitleAlreadyExistsException.class)
    public ApiErrorResponse handleTitleAlreadyExistsException(TitleAlreadyExistsException exception) {
        return getApiErrorResponse(exception, HttpStatus.CONFLICT, TITLE_ALREADY_EXISTS);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(TaskNotFoundException.class)
    public ApiErrorResponse handleTaskNotFoundException(TaskNotFoundException exception) {
        return getApiErrorResponse(exception, HttpStatus.CONFLICT, TASK_NOT_FIND);
    }

    private ApiErrorResponse getApiErrorResponse(Exception exception, HttpStatus status, String description) {
        String code = status.toString();
        String exceptionName = exception.getClass().getName();
        String exceptionMessage = exception.getMessage();
        String[] stacktrace = getStacktrace(exception);

        return new ApiErrorResponse(description, code, exceptionName, exceptionMessage, stacktrace);
    }

    private String[] getStacktrace(Exception exception) {
        StackTraceElement[] stackTraceElements = exception.getStackTrace();
        String[] stacktrace = new String[stackTraceElements.length];
        for (int i = 0; i < stackTraceElements.length; i++) {
            stacktrace[i] = stackTraceElements[i].toString();
        }
        return stacktrace;
    }
}
