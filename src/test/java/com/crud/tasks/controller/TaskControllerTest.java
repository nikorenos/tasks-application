package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldFetchEmptyTasksList() throws Exception {
        // Given
        List<Task> taskList = new ArrayList<>();
        when(service.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(new ArrayList<>());


        // When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) //or isOk()
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchTasksList() throws Exception {
        // Given
        List<TaskDto> taskList = new ArrayList<>();
        taskList.add(new TaskDto(1L, "Task 1", "Task 1 content"));

        //when(taskController.getTasks()).thenReturn(taskList);

        // When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Task 1")))
                .andExpect(jsonPath("$[0].content", is("Task 1 content")));
    }

    @Test
    public void shouldFetchTask() throws Exception {
        // Given
        TaskDto taskDto = new TaskDto(1L, "Task 1", "Task 1 content");
        Optional<Task> task = Optional.of(new Task(1L, "Task 1", "Task 1 content"));
        long taskId = taskDto.getId();

        when(service.getTask(taskId)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task.get())).thenReturn(taskDto);

        // When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Task 1")))
                .andExpect(jsonPath("$.content", is("Task 1 content")));
    }

    @Test
    public void shouldFetchTaskById() throws Exception {
        // Given
        TaskDto taskDto = new TaskDto(1L, "Task 1", "Task 1 content");
        Optional<Task> task = Optional.of(new Task(1L, "Task 1", "Task 1 content"));
        long taskId = taskDto.getId();

        when(service.getTaskById(taskId)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task.get())).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        // When & Then
        mockMvc.perform(get("/v1/task/getTaskById").param("taskId","1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Task 1")))
                .andExpect(jsonPath("$.content", is("Task 1 content")));
    }

}