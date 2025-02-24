package com.inn.orders.entities;

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
@Table(name = "order_taxes")
@Data
public class OrderTaxe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_taxe_id")
    private Long orderId;
    
    @Column(name = "type_tax_id")
    private Long typeTaxId;
    
    @Column(name = "payment_order_details_id")
    private Long paymentOrderDetailId;
    
    @ManyToOne
	@JoinColumn(name = "type_tax_id", referencedColumnName = "type_taxe_id", nullable = false, insertable = false, updatable = false)
    private TypeTaxe typeTaxe;
}