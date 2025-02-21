package com.inn.products.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "lots")
@Data
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id")
    private Long lotId;

    @Column(name = "product_id")
    private Long productId;
    @Column(name = "lot_number")
    private String lotNumber;
    @Column(name = "manufacture_date")
    private Date manufactureDate;
    @Column(name = "expiration_date")
    private Date expirationDate;
    @Column(name = "initual_quantity")
    private Long initialQuantity;
}