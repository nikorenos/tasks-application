package com.crud.tasks.dto;

import com.crud.tasks.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String firstname;
    private String lastname;
    private String pesel;
    private List<Task> tasks = new ArrayList<>();
}
