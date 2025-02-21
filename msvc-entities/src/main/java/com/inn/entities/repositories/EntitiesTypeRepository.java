package com.inn.entities.repositories;

import com.inn.entities.entities.EntitiesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntitiesTypeRepository extends JpaRepository<EntitiesType, Long> {
}