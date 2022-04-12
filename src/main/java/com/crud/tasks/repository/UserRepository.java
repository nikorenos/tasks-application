package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();
    @Override
    Optional<User> findById(Long taskId);

    @Override
    void deleteById(Long taskId);
}


