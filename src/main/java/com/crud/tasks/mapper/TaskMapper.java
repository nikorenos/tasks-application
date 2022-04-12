package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.dto.TaskDto;
import com.crud.tasks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {
    @Autowired
    private UserService userService;

    public Task mapToTask(final TaskDto taskDto) {
        if (taskDto.getUserId() == null) {
            return new Task(
                    taskDto.getId(),
                    taskDto.getTitle(),
                    taskDto.getContent(),
                    null);
        } else {
            return new Task(
                    taskDto.getId(),
                    taskDto.getTitle(),
                    taskDto.getContent(),
                    taskDto.getUserId());
        }
    }

    public TaskDto mapToTaskDto(final Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getContent(),
                task.getUserId());
    }

    public List<TaskDto> mapToTaskDtoList(final List<Task> taskList) {
        return taskList.stream()
                .map(t -> new TaskDto(t.getId(), t.getTitle(), t.getContent(), t.getUserId()))
                .collect(Collectors.toList());
    }
}
