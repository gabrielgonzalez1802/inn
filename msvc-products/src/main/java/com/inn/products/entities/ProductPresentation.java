package com.inn.products.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products_presentations")
@Data
public class ProductPresentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_presentation_id")
    private Long presentationId;

    @Column(name = "presentation_name", unique = true, nullable = false)
    private String presentationName;
}