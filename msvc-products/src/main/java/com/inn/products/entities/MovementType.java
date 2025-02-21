package com.inn.products.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "movements_types")
@Data
public class MovementType {

    @Id
    @Column(name = "movement_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movementTypeId;

    @Column(name = "movement_type", nullable = false, unique = true)
    private String movementType;
}