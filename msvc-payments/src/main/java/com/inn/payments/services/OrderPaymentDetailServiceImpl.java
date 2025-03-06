package com.inn.payments.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.commons.dtos.OrderDTO;
import com.inn.commons.enums.OrderStatus;
import com.inn.commons.exceptions.ResourceNotFoundException;
import com.inn.payments.clients.OrderClientRest;
import com.inn.payments.dtos.OrderPaymentDetailDto;
import com.inn.payments.entities.OrderPaymentDetail;
import com.inn.payments.repositories.OrderPaymentDetailRepository;

@Service
public class OrderPaymentDetailServiceImpl implements OrderPaymentDetailService {

    @Autowired
    private OrderPaymentDetailRepository orderPaymentDetailRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private OrderClientRest orderClientFeing;

    @Override
    public List<OrderPaymentDetailDto> findAll() {
        return orderPaymentDetailRepository.findAll().stream()
                .map(orderPaymentDetail -> modelMapper.map(orderPaymentDetail, OrderPaymentDetailDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderPaymentDetailDto findById(Long id) {
        OrderPaymentDetail orderPaymentDetail = orderPaymentDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle de pago no encontrado con id: " + id));
        return modelMapper.map(orderPaymentDetail, OrderPaymentDetailDto.class);
    }

    /**
     * Saves an OrderPaymentDetailDto object.
     *
     * @param orderPaymentDetailDto the OrderPaymentDetailDto object to be saved
     * @return the saved OrderPaymentDetailDto object
     * @throws ResourceNotFoundException if the associated order is not found
     */
    @Override
    public OrderPaymentDetailDto save(OrderPaymentDetailDto orderPaymentDetailDto) {
    	//buscamos la orden asociada
    	OrderDTO order = orderClientFeing.getOrderById(orderPaymentDetailDto.getOrderId());
    	
        OrderPaymentDetail orderPaymentDetail = modelMapper.map(orderPaymentDetailDto, OrderPaymentDetail.class);
        
        orderPaymentDetail = orderPaymentDetailRepository.save(orderPaymentDetail);
        //si la orden de pago fue creada
		if (orderPaymentDetail.getPaymentDetailId() != null) {
			//se creara una nueva orden de pago
			if(order!=null) {
				//actualizamos el estado de la orden a VALIDANDO_PAGO
				orderClientFeing.updateOrderStatus(order.getOrderId(), OrderStatus.VALIDANDO_PAGO.getValor());
			}
        }
        
        return modelMapper.map(orderPaymentDetail, OrderPaymentDetailDto.class);
    }

    @Override
    public void deleteById(Long id) {
        if (!orderPaymentDetailRepository.existsById(id)) {
            throw new ResourceNotFoundException("Detalle de pago no encontrado con id: " + id);
        }
        orderPaymentDetailRepository.deleteById(id);
    }

	@Override
	public OrderPaymentDetailDto findByOrderId(Long orderId) {
        OrderPaymentDetail orderPaymentDetail = orderPaymentDetailRepository.findByOrderId(orderId);
        		
        if(orderPaymentDetail==null) {
        	throw new ResourceNotFoundException("Detalle de pago no encontrado con id de orden: " + orderId);
        }
        
        return modelMapper.map(orderPaymentDetail, OrderPaymentDetailDto.class);
	}
}