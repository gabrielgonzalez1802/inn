package com.inn.payments.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "payment_taxes")
public class PaymentTax {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_tax_id")
    private Long payTaxId;

    @Column(name = "type_taxes_id")
    private Long typeTaxesId;

    @ManyToOne
    @JoinColumn(name = "payment_datail_id")
    private OrderPaymentDetail paymentDetail;
}