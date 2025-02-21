package com.inn.products.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_products")
@Data
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ord_prod_id")
    private Long ordProdId;

    @Column(name = "order_id", nullable = false)
    private Long orderId;
    @Column(name = "product_id", nullable = false)
    private Long productId;
}