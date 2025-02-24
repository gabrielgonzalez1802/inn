package com.inn.entities.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "entities")
public class Entities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entity_id")
    private Long entityId;

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
    
    @Column(name = "entity_type_id", nullable = false)
    private Long entityTypeId; 
    
    @ManyToOne
	@JoinColumn(name = "entity_type_id", referencedColumnName = "entity_type_id", nullable = false, insertable = false, updatable = false)
    private EntitiesType entityType;
}