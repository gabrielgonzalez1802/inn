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
@Table(name = "type_taxes")
public class TypeTax {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_tax_id")
    private Long typeTaxeId;

    @Column(name = "tax_name", nullable = false, unique = true)
    private String taxName;

    @Column(name = "tax_percentaje", nullable = false)
    private Integer taxPercentaje;
}