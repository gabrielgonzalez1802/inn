package com.inn.users.services;

import java.util.List;
import java.util.Optional;

import com.inn.users.entities.User;

public interface IUserService {

    Optional<User> findById(Long id);

	Optional<User> findByUsername(String username);

    List<User> findAll();

    User save(User user);
    
    Optional<User> update(User user, Long id);

    void delete(Long id);
}