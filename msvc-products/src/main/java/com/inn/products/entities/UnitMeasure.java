package com.inn.products.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "unit_measures")
@Data
public class UnitMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unitMeasureId;

    private String unitMeasureName;
    private String unitMeasureAbbreviation;
}