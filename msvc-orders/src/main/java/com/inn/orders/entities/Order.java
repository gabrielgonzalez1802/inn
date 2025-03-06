package com.inn.orders.entities;

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
@Table(name = "orders")
@Data
public class Order {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "order_status_id", nullable = false)
    private Long orderStatusId;
    
    @Column(name = "warehouse_id", nullable = false)
    private Long warehouseId;
    
    @Column(name = "entity_id")
    private Long entityId;
    
    @Column(name = "lot_id")
    private Long lotId;
    
    @Column(name = "sunagro_guide_id")
    private Long sunagroGuideId;
    
    @Column(name = "delivery_note")
    private String deliveryNote;
    
    @Column(name = "observations")
    private String observations;
    
    @Column(name = "responsible_user")
    private String responsibleUser;
    
    @Column(name = "registration_datetime")
    private LocalDateTime registrationDatetime;
    
    @ManyToOne
	@JoinColumn(name = "order_status_id", referencedColumnName = "order_status_type_id", nullable = false, insertable = false, updatable = false)
    private OrderStatusType orderStatusType;
}