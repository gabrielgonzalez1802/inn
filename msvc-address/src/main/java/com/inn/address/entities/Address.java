package com.inn.address.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "entity_id")
    private Long entityId;

    @Column(name = "address")
    private String address;
    
    @Column(name = "city_id", nullable = false)
    private Long cityId;
    
    @Column(name = "state_id", nullable = false)
    private Long stateId;

    @Column(name = "address_comment")
    private String addressComment;
    
    @ManyToOne
	@JoinColumn(name = "city_id", referencedColumnName = "city_id", nullable = false, insertable = false, updatable = false)
    private City city;
}