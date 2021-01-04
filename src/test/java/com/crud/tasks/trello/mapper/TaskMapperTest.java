package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {
    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void mapToTaskTest() {
        // Given
        TaskDto taskDto = new TaskDto(1L, "Task 1", "Content task 1");

        // When
        Task task = taskMapper.mapToTask(taskDto);

        // Then
        assertEquals(1L, (long)task.getId());
        assertEquals("Task 1", task.getTitle());
        assertEquals("Content task 1", task.getContent());
    }

    @Test
    public void mapToTaskDtoTest() {
        // Given
        Task task = new Task(1L, "Task 1", "Content task 1");

        // When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        // Then
        assertEquals(1L, (long)taskDto.getId());
        assertEquals("Task 1", taskDto.getTitle());
        assertEquals("Content task 1", taskDto.getContent());
    }

    @Test
    public void mapToTaskDtoListTest() {
        // Given
        Task task = new Task(1L, "Task 1", "Content task 1");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);

        // When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        // Then
        assertEquals(1L, (long)taskDtoList.get(0).getId());
        assertEquals("Task 1", taskDtoList.get(0).getTitle());
        assertEquals("Content task 1", taskDtoList.get(0).getContent());
    }

}