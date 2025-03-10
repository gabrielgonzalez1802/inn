package com.inn.payments.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "currency_type")
public class CurrencyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_id")
    private Long currencyId;

    @Column(name = "currency_name", unique = true, nullable = false)
    private String currencyName;
}