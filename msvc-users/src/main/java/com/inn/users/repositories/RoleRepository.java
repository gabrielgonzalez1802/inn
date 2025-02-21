package com.inn.users.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.inn.users.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
    Optional<Role> findByName(String name);
}
