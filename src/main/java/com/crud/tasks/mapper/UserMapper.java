package com.crud.tasks.mapper;

import com.crud.tasks.domain.User;
import com.crud.tasks.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(
                null,
                userDto.getFirstname(),
                userDto.getLastname(),
                userDto.getPesel(),
                userDto.getTasks());
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getFirstname(),
                user.getLastname(),
                user.getPesel(),
                user.getTasks());
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(t -> new UserDto(t.getFirstname(), t.getLastname(), t.getPesel(), t.getTasks()))
                .collect(Collectors.toList());
    }
}

