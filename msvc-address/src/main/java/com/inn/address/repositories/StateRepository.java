package com.inn.address.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.address.entities.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
}