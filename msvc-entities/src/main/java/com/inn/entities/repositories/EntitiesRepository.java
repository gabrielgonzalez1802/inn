package com.inn.entities.repositories;

import com.inn.entities.entities.Entities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntitiesRepository extends JpaRepository<Entities, Long> {
}