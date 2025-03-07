package com.inn.address.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.address.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	List<City> findByStateId(Long stateId);
}