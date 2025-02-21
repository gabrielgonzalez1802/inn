package com.inn.payments.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.payments.dtos.OrderPaymentDetailDto;
import com.inn.payments.entities.OrderPaymentDetail;
import com.inn.payments.exceptions.ResourceNotFoundException;
import com.inn.payments.repositories.OrderPaymentDetailRepository;

@Service
public class OrderPaymentDetailServiceImpl implements OrderPaymentDetailService {

    @Autowired
    private OrderPaymentDetailRepository orderPaymentDetailRepository;

    @Autowired
    private ModelMapper modelMapper;

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

    @Override
    public OrderPaymentDetailDto save(OrderPaymentDetailDto orderPaymentDetailDto) {
        OrderPaymentDetail orderPaymentDetail = modelMapper.map(orderPaymentDetailDto, OrderPaymentDetail.class);
        orderPaymentDetail = orderPaymentDetailRepository.save(orderPaymentDetail);
        return modelMapper.map(orderPaymentDetail, OrderPaymentDetailDto.class);
    }

    @Override
    public void deleteById(Long id) {
        if (!orderPaymentDetailRepository.existsById(id)) {
            throw new ResourceNotFoundException("Detalle de pago no encontrado con id: " + id);
        }
        orderPaymentDetailRepository.deleteById(id);
    }
}