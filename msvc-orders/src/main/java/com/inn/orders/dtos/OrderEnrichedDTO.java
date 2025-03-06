package com.inn.orders.dtos;

import java.util.List;

import com.inn.commons.dtos.EntityAddressDTO;
import com.inn.commons.dtos.OrderDTO;

import lombok.Data;

@Data
public class OrderEnrichedDTO {

    private OrderDTO order;
    
    private EntitiesDTO entity;
    
    private OrderPaymentDetailDto orderPaymentDetail;
    
    private List<EntityAddressDTO> entitesAddress;

    private List<OrderProductDTO> ordersProducts;
}