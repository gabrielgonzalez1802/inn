package com.inn.entities.repositories;

import com.inn.entities.entities.SumagroGuides;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SumagroGuidesRepository extends JpaRepository<SumagroGuides, Long> {
}