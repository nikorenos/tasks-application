package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.User;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    TaskRepository repository;

    @Test
    public void getAllTasks() {
        //Given
        User user = new User(1L, "Marcin", "Kotlin", "1234", null);
        Task task1 = new Task(1L, "Task 1", "Task 1 content", 1L);
        Task task2 = new Task(2L, "Task 2", "Task 2 content", null);
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        user.setTasks(taskList);
        taskList.add(task2);


        when(repository.findAll()).thenReturn(taskList);

        // When
        List<Task> receivedList = taskService.getAllTasks();
        // Then
        assertEquals(2,receivedList.size());
        assertEquals("1",receivedList.get(0).getUserId().toString());
    }

    @Test
    public void getTaskById() {
        //Given
        Optional<Task> task1 = Optional.of(new Task(1L, "Task 1", "Task 1 content", null));
        long taskId = task1.get().getId();

        when(repository.findById(taskId)).thenReturn(task1);

        // When
        Optional<Task> receivedTask = taskService.getTask(taskId);
        // Then
        assertEquals("Task 1",receivedTask.get().getTitle());
        assertEquals("Task 1 content",receivedTask.get().getContent());
    }

    @Test
    public void getTask() {
        //Given
        Optional<Task> task1 = Optional.of(new Task(1L, "Task 1", "Task 1 content", null));
        long taskId = task1.get().getId();

        when(repository.findById(taskId)).thenReturn(task1);

        // When
        Optional<Task> receivedTask = taskService.getTask(taskId);
        // Then
        assertEquals("Task 1",receivedTask.get().getTitle());
        assertEquals("Task 1 content",receivedTask.get().getContent());
    }

    @Test
    public void saveTask() {
        //Given
        Task task1 = new Task(1L, "Task 1", "Task 1 content", null);

        when(repository.save(task1)).thenReturn(task1);

        // When
        Task savedTask = taskService.saveTask(task1);
        // Then
        assertEquals("Task 1",savedTask.getTitle());
        assertEquals("Task 1 content",savedTask.getContent());
    }

    @Test
    public void deleteTaskById() {
        // When
        taskService.deleteById(1L);

        // Then
        Mockito.verify(repository, times(1)).deleteById(1L);
    }
}