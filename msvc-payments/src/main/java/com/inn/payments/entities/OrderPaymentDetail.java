package com.inn.payments.entities;

import java.math.BigDecimal;

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
@Table(name = "order_payment_detail")
public class OrderPaymentDetail {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_datail_id")
    private Long paymentDetailId;

    @Column(name = "order_full_price", nullable = false)
    private BigDecimal orderFullPrice;
    
    @Column(name = "order_id", unique = true, nullable = false)
    private Long orderId;
    
    @Column(name = "currency_id", nullable = false)
    private Long currencyId;
    
    @Column(name = "payment_type_id", nullable = false)
    private Long paymentTypeId;

    @ManyToOne
	@JoinColumn(name = "currency_id", referencedColumnName = "currency_id", nullable = false, insertable = false, updatable = false)
    private CurrencyType currency;
    
    @ManyToOne
	@JoinColumn(name = "payment_type_id", referencedColumnName = "payment_type_id", nullable = false, insertable = false, updatable = false)
    private PaymentType paymentType;
}