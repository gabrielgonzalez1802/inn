package com.inn.entities.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "sumagro_guides")
public class SumagroGuides {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sunagro_guides_id")
    private Long sumagroGuidesId;

    @Column(name = "guide_number", nullable = false, length = 20)
    private String guideNumber;

    @Column(name = "emission_date", nullable = false)
    private Date emissionDate;

    @ManyToOne
    @JoinColumn(name = "entity_id", nullable = false)
    private Entities entity;
}