package com.inn.entities.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "entities")
public class Entities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entity_id")
    private Long entityId;

    @ManyToOne
    @JoinColumn(name = "entity_type_id", nullable = false)
    private EntitiesType entityType;

    @Column(name = "entity_name", nullable = false, length = 50)
    private String entityName;

    @Column(name = "entity_last_name", nullable = false, length = 50)
    private String entityLastName;

    @Column(name = "entity_email", nullable = false, length = 100)
    private String entityEmail;

    @Column(name = "entity_password", nullable = false, length = 100)
    private String entityPassword;

    @Column(name = "entity_phone", nullable = false, length = 15)
    private String entityPhone;
}