package com.inn.entities.entities;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "entities_type")
public class EntitiesType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entity_type_id")
    private Long entityTypeId;

    @Column(name = "entity_type_name", nullable = false, length = 50)
    private String entityTypeName;
}