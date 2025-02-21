package com.inn.products.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "stock")
@Data
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "product_id")
    private Long productId;
    @Column(name = "quality")
    private Long quality;
    @Column(name = "warehouse_id")
    private Long warehouseId;
}