package com.inn.users.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.inn.users.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
