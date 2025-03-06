package com.inn.warehouses.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;

    @Column(nullable = false)
    private String warehouseName;

    @Column(name = "address_id")
    private Long addressId;

    @Column(nullable = false)
    private String warehousePhone;

    @Column(nullable = false)
    private String warehouseContact;
}