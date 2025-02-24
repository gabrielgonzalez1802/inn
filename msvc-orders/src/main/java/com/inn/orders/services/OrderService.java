package com.inn.orders.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.orders.clients.AddressClientRest;
import com.inn.orders.clients.EntityClientRest;
import com.inn.orders.clients.PaymentClientRest;
import com.inn.orders.clients.ProductsClientRest;
import com.inn.orders.dtos.EntitiesDTO;
import com.inn.orders.dtos.EntityAddressDTO;
import com.inn.orders.dtos.OrderDTO;
import com.inn.orders.dtos.OrderEnrichedDTO;
import com.inn.orders.dtos.OrderPaymentDetailDto;
import com.inn.orders.dtos.OrderProductDTO;
import com.inn.orders.entities.Order;
import com.inn.orders.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private EntityClientRest entityClientFeign;
	
	@Autowired
	private AddressClientRest addressClientFeign;
	
	@Autowired
	private PaymentClientRest paymentClientFeign;
	
	@Autowired
	private ProductsClientRest productClientFeign;
	
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    public OrderEnrichedDTO findOrderEnrichedById(Long id) {
    	
    	Order order = null;
    	Optional<Order> orderOptional = orderRepository.findById(id);
		if(orderOptional.isPresent()) {
    		order = orderOptional.get();
    	}
    	
    	OrderEnrichedDTO orderEnrichedDTO = new OrderEnrichedDTO();
    	
    	if(order!=null) {
    		EntitiesDTO entityDTO = entityClientFeign.getEntityById(order.getClientId());
    		OrderPaymentDetailDto orderPaymentDetailDTO = paymentClientFeign.findByOrderId(order.getOrderId());
    		List<EntityAddressDTO> entityAddressListDTO = addressClientFeign.getAllByEntityId(order.getOrderId());
    		List<OrderProductDTO> ordersProductsListDTO = productClientFeign.getAllOrderProductByOrderId(order.getOrderId());
    		
    		orderEnrichedDTO.setOrder(modelMapper.map(order, OrderDTO.class));
    		orderEnrichedDTO.setEntity(entityDTO);
    		orderEnrichedDTO.setOrderPaymentDetail(orderPaymentDetailDTO);
    		orderEnrichedDTO.setEntitesAddress(entityAddressListDTO);
    		orderEnrichedDTO.setOrdersProducts(ordersProductsListDTO);
    	}
    	
        return orderEnrichedDTO;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
    
    public List<Order> findAllByClientId(Long clientId) {
        return orderRepository.findByClientId(clientId);
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }
   
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}