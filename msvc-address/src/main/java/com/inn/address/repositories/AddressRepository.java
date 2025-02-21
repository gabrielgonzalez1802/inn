package com.inn.address.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inn.address.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}