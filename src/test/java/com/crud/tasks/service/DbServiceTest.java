package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    TaskRepository repository;

    @Test
    public void getAllTasks() {
        //Given
        Task task1 = new Task(1L, "Task 1", "Task 1 content");
        Task task2 = new Task(2L, "Task 2", "Task 2 content");
        List<Task> list = new ArrayList<>();
        list.add(task1);
        list.add(task2);

        when(repository.findAll()).thenReturn(list);

        // When
        List<Task> receivedList = dbService.getAllTasks();
        // Then
        assertEquals(2,receivedList.size());
        assertEquals("Task 2",receivedList.get(1).getTitle());
    }
}