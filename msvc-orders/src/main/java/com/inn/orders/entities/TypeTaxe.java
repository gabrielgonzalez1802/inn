package com.inn.orders.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "type_taxes")
@Data
public class TypeTaxe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_taxe_id")
    private Long typeTaxeId;
  
    @Column(name = "nameTaxe", unique = true, nullable = false)
    private String nameTaxe;
}