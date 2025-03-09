package com.inn.orders.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.commons.dtos.EntityAddressDTO;
import com.inn.commons.dtos.OrderDTO;
import com.inn.commons.dtos.ProductDTO;
import com.inn.commons.enums.OrderStatus;
import com.inn.commons.exceptions.ResourceNotFoundException;
import com.inn.orders.clients.AddressClientRest;
import com.inn.orders.clients.EntityClientRest;
import com.inn.orders.clients.PaymentClientRest;
import com.inn.orders.clients.ProductsClientRest;
import com.inn.orders.dtos.EnrichedOrderDTO;
import com.inn.orders.dtos.EntitiesDTO;
import com.inn.orders.dtos.OrderEnrichedDTO;
import com.inn.orders.dtos.OrderPaymentDetailDto;
import com.inn.orders.dtos.OrderProductDTO;
import com.inn.orders.entities.Order;
import com.inn.orders.exceptions.OrderNotCreatedException;
import com.inn.orders.repositories.OrderRepository;

import jakarta.validation.Valid;

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
    		EntitiesDTO entityDTO = entityClientFeign.getEntityById(order.getEntityId());
    		OrderPaymentDetailDto orderPaymentDetailDTO = new OrderPaymentDetailDto();
    		List<EntityAddressDTO> entityAddressListDTO = new LinkedList<EntityAddressDTO>();
    		List<OrderProductDTO> ordersProductsListDTO = new LinkedList<OrderProductDTO>();
    		
			try {
				orderPaymentDetailDTO = paymentClientFeign.findByOrderId(order.getOrderId());
	    		orderEnrichedDTO.setOrderPaymentDetail(orderPaymentDetailDTO);
			} catch (Exception e) {
	    		orderEnrichedDTO.setOrderPaymentDetail(orderPaymentDetailDTO);
			}
			try {
				entityAddressListDTO = addressClientFeign.getAllByEntityId(order.getEntityId());
				orderEnrichedDTO.setEntitesAddress(entityAddressListDTO);
			} catch (Exception e) {    		
				orderEnrichedDTO.setEntitesAddress(entityAddressListDTO);
			}
			try {
				ordersProductsListDTO = productClientFeign.getAllOrderProductByOrderId(order.getOrderId());
	    		orderEnrichedDTO.setOrdersProducts(ordersProductsListDTO);
			} catch (Exception e) {
	    		orderEnrichedDTO.setOrdersProducts(ordersProductsListDTO);
			}
    		
    		orderEnrichedDTO.setOrder(modelMapper.map(order, OrderDTO.class));
    		orderEnrichedDTO.setEntity(entityDTO);
    	}
    	
        return orderEnrichedDTO;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
    
    public List<Order> findAllByEntityId(Long entityId) {
        return orderRepository.findByEntityId(entityId);
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
    
    /**
     * Method to change the status of an order.
     * 
     * @param id the ID of the order to be updated
     * @param orderStatus the new status to be set for the order
     * @throws ResourceNotFoundException if the order is not found
     */
	public void updateOrderStatus(Long id, Long orderStatus) {
//		 TODO Se puede generar dato histórico de quen modifico el estado de la orden
		Order order = orderRepository.findById(id).orElse(null);
		
		//revisamos si el estato de la orden es el correcto
		OrderStatus.fromValor(orderStatus);
		
		if (order != null) {
			order.setOrderStatusId(orderStatus);
			orderRepository.save(order);
		}else {
			throw new ResourceNotFoundException("No se pudo cambiar el estado de la orden ya que la orden no existe");
		}
	}
    
	/**
	 * Método para guardar una orden, si no se pueden guardar todos los productos, se elimina la orden
	 * @param enrichedOrderDTO
	 * @return Order en caso de éxito, erro	r en caso contrario
	 */
	public Order saveEnrichedOrder(@Valid EnrichedOrderDTO enrichedOrderDTO) {
				
		OrderDTO order = null;
		Order orderEntity = null;
		List<Long> productsNotFound = new LinkedList<>();
		
		if(enrichedOrderDTO.getOrder()!=null) {
			//primero creamos la orden
			order = enrichedOrderDTO.getOrder();
			order.setOrderStatusId(OrderStatus.POR_APROBAR.getValor());

			orderEntity = orderRepository.save(modelMapper.map(enrichedOrderDTO.getOrder(), Order.class));
	        if(orderEntity!=null) {
	        	// guardamos los productos de la orden
	        	for (ProductDTO productDTO : enrichedOrderDTO.getProducts()) {
	        		try {
		        		productClientFeign.createOrderProduct(new OrderProductDTO(orderEntity.getOrderId(), productDTO.getProductId()));
					} catch (Exception e) {
						productsNotFound.add(productDTO.getProductId());
					}
	        	}
	        	
				if (productsNotFound.size() > 0) {
					// si no se pudieron guardar todos los productos, eliminamos la orden
					orderRepository.deleteById(order.getOrderId());
		    		throw new OrderNotCreatedException(productsNotFound);
				}
	        }
		}
		
		order.setOrderId(orderEntity.getOrderId());
		return modelMapper.map(order, Order.class);
	}
}