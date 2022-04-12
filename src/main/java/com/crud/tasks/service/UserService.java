package com.crud.tasks.service;

import com.crud.tasks.domain.User;
import com.crud.tasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(final Long userId) {
        return userRepository.findById(userId);
    }

    public void deleteById(final Long userId) {
        userRepository.deleteById(userId);
    }
}

