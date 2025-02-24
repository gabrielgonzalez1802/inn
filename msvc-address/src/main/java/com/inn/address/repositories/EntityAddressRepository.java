package com.inn.address.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.address.entities.EntityAddress;

@Repository
public interface EntityAddressRepository extends JpaRepository<EntityAddress, Long> {
	List<EntityAddress> findByEntityId(Long entityId);
}