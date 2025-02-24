package com.inn.orders.dtos;

import lombok.Data;

@Data
public class OrderTaxeDTO {

    private Long orderId;
    private Long typeTaxId;
    private Long paymentOrderDetailId;
    
    private TypeTaxeDTO typeTaxe;
}