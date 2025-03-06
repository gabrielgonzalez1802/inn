package com.inn.products.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "stock")
public class Stock {
	
    @EmbeddedId
    private StockId id;
    
    @Column(name = "quantity")
    private Long quantity;

    @ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false)
    private Product product;
}