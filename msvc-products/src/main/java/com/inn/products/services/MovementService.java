package com.inn.products.services;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.commons.dtos.OrderDTO;
import com.inn.commons.enums.OrderStatus;
import com.inn.commons.enums.TipoMovimiento;
import com.inn.products.clients.OrderClientRest;
import com.inn.products.dtos.ProductList;
import com.inn.products.dtos.ProductMovementSummaryDTO;
import com.inn.products.dtos.ProductMovementWeeklyDTO;
import com.inn.products.entities.Movement;
import com.inn.products.entities.Stock;
import com.inn.products.entities.StockId;
import com.inn.products.exceptions.ProductsNotFoundException;
import com.inn.products.repositories.MovementRepository;
import com.inn.products.repositories.StockRepository;

import jakarta.validation.Valid;

@Service
public class MovementService {

    @Autowired
    private MovementRepository movementRepository;
  
    @Autowired
    private StockRepository stockRepository;
    
    @Autowired
    private OrderClientRest orderClientFeing;

    public List<Movement> findAll() {
        return movementRepository.findAll();
    }

    public Optional<Movement> findById(Long id) {
        return movementRepository.findById(id);
    }

    public Movement save(Movement Movement) {
        return movementRepository.save(Movement);
    }

    public void deleteById(Long id) {
        movementRepository.deleteById(id);
    }
    
    public List<ProductMovementSummaryDTO> findMovementsGroupedByProductName(LocalDateTime startDate, LocalDateTime endDate, 
    		Long warehouseId, List<Long> movementTypeIds) {
    	
    	List<Object[]> listaMovimientos = movementRepository.getMovementsSummaryGroupedByProductWithInvFinalWithAllProducts(startDate, endDate, warehouseId, movementTypeIds);
    	List<ProductMovementSummaryDTO> listaMovimientosDTO = new LinkedList<>();
    	
		ProductMovementSummaryDTO movementDTOTempForIssue = new ProductMovementSummaryDTO(1l, "",
				0.0, 0.0, 0.0, 0.0, 0.0);
		
		if(!listaMovimientos.isEmpty()) {
			listaMovimientosDTO.add(movementDTOTempForIssue);
		}
    	
		for (Object[] result : listaMovimientos) {
			Long productId = (Long) result[0];
			String productName = (String) result[1];
			Double totalRecepcion = ((Long) result[2]).doubleValue();
			Double totalDespacho = ((Long) result[3]).doubleValue();
			Double totalMerma = ((Long) result[4]).doubleValue();
			Double totalInvInicial = ((Long) result[5]).doubleValue();
			Double totalInvFinal = ((Long) result[6]).doubleValue();

			ProductMovementSummaryDTO movementDTO = new ProductMovementSummaryDTO(productId, productName,
					totalRecepcion, totalDespacho, totalMerma, totalInvInicial, totalInvFinal);

			listaMovimientosDTO.add(movementDTO);
		}
    	
    	return listaMovimientosDTO;
    }
    
    public boolean fechaNoExcedeSemana(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        long diferenciaDias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        return diferenciaDias <= 7;
    }
    
    public boolean fechaNoExcedeMes(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        Period periodo = Period.between(fechaInicio.toLocalDate(), fechaFin.toLocalDate());
        return periodo.getMonths() <= 1 && periodo.getYears() == 0; // Verifica que no haya pasado más de un mes y que no haya cambiado el año.
    }

	public List<ProductMovementWeeklyDTO> findMovementsMonthsGroupedByProductName(LocalDateTime fechaInicio,
			LocalDateTime fechaFin, Long warehouseId, Long movementTypeId) {
		
    	List<Object[]> listaMovimientos = movementRepository.findMovementsSummaryByMovementTypeAndWeekly(fechaInicio, fechaFin, warehouseId, movementTypeId);
    	List<ProductMovementWeeklyDTO> listaMovimientosDTO = new LinkedList<>();
    	
    	ProductMovementWeeklyDTO movementDTOTempForIssue = new ProductMovementWeeklyDTO(1l, "",
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		
		if(!listaMovimientos.isEmpty()) {
			listaMovimientosDTO.add(movementDTOTempForIssue);
		}
    	
		for (Object[] result : listaMovimientos) {
			Long productId = (Long) result[0];
			String productName = (String) result[1];
			Double totalMovimiento = ((Long) result[2]).doubleValue();
			Double semana1 = ((Long) result[3]).doubleValue();
			Double semana2 = ((Long) result[4]).doubleValue();
			Double semana3 = ((Long) result[5]).doubleValue();
			Double semana4 = ((Long) result[6]).doubleValue();
			Double semana5 = ((Long) result[7]).doubleValue();

			ProductMovementWeeklyDTO movementDTO = new ProductMovementWeeklyDTO(productId, productName,
					totalMovimiento, semana1, semana2, semana3, semana4, semana5);

			listaMovimientosDTO.add(movementDTO);
		}
    	
    	return listaMovimientosDTO;
	}

	/**
	 * Saves a list of product movements.
	 *
	 * @param listProducts the list of products to be moved
	 * @return a list of saved movements
	 * @throws ProductsNotFoundException if any product does not have sufficient stock
	 */
	public List<Movement> saveByListProducts(@Valid ProductList listProducts) {
				
		//verificamos que la orden exista
		OrderDTO orderDTO = orderClientFeing.getOrderById(listProducts.getOrderId());
		
		List<Long> listProductsNotFound = new LinkedList<>();
		
		// revisamos que los productos existan y tengan inventario suficiente en el almacen, de lo contratario se informa al usuario
		listProducts.getProducts().forEach(product -> {
			stockRepository.findById(new StockId(product.getId(), product.getWarehouseId())).ifPresent(stock -> {
				if (stock.getQuantity() < product.getCantidad()) {
					listProductsNotFound.add(product.getId());
				}
			});
		});
		
		if (!listProductsNotFound.isEmpty()) {
			throw new ProductsNotFoundException(listProductsNotFound);
		}
		
		//creamos los movimientos
		List<Movement> listaMovimientos = new LinkedList<>();
		
		listProducts.getProducts().forEach(product -> {
			
			TipoMovimiento tipoMovimiento = TipoMovimiento.fromValor(product.getMovementTypeId());
			
			Movement movimiento = new Movement();
			movimiento.setMovementDate(LocalDateTime.now());
			movimiento.setMovementTypeId(product.getMovementTypeId());
			movimiento.setProductId(product.getId());
			movimiento.setQuantity(product.getCantidad());
			movimiento.setWarehouseId(product.getWarehouseId());
			movimiento.setOrderId(orderDTO.getOrderId());
			movimiento.setActionDescription(null); //TODO.ggonzalez revisar si este campo es necesario
			
			Movement newMovement = movementRepository.save(movimiento); //guardamos el movimiento
			if(newMovement!=null) {
				//buscamos y actualizamos el stock
				Stock stock = stockRepository.findById(new StockId(product.getId(), product.getWarehouseId())).get();
				if (tipoMovimiento == TipoMovimiento.RECEPCION || tipoMovimiento == TipoMovimiento.INV_INICIAL) {
					stock.setQuantity(stock.getQuantity() + product.getCantidad());
				} else if (tipoMovimiento == TipoMovimiento.DESPACHO || tipoMovimiento == TipoMovimiento.MERMA) {
					stock.setQuantity(stock.getQuantity() - product.getCantidad());
				}
				stockRepository.save(stock);
				
				listaMovimientos.add(newMovement);
				//TODO.ggonzalez revisar guardado de auditoria
			}
		});
		
		//finalizamos la orden
		orderClientFeing.updateOrderStatus(orderDTO.getOrderId(), OrderStatus.CERRADA.getValor());
		
		return listaMovimientos;
	}
}