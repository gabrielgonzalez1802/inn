package com.inn.products.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "movements")
@Data
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movement_id")
    private Long movementId;

    @Column(name = "product_id", nullable = false)
    private Long productId;
 
    @Column(name = "movement_date", nullable = false)
    private LocalDateTime movementDate;
    
    @Column(name = "action_description")
    private String actionDescription;
    
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "movement_type_id", nullable = false)
    private Long movementTypeId;
    
    @Column(name = "order_id")
    private Long orderId;
    
    @Column(name = "warehouse_id", nullable = false)
    private Long warehouseId;
    
    @ManyToOne
    @JoinColumn(name = "movement_type_id", nullable = false, insertable = false, updatable = false)
    private MovementType movementType;
    
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, insertable = false, updatable = false)
    private Product product; 
}