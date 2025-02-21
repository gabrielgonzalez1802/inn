package com.inn.orders.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "order_status_history")
@Data
public class OrderStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_status_history_id")
    private Long orderStatusHistoryId;

    @Column(name = "system_user_id", nullable = false)
    private Long systemUserId;

    @Column(name = "type_order_status_id", nullable = false)
    private Long typeOrderStatusId;

    @Column(name = "order_id", nullable = false)
    private Long orderId;
}