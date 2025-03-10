package com.inn.products.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inn.products.entities.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

	@Query("SELECT m.product.productName, m.movementType.movementType, m.movementType.movementTypeId, SUM(m.quantity) " + "FROM Movement m "
			+ "WHERE m.movementDate BETWEEN :startDate AND :endDate " + "AND m.warehouseId = :warehouseId "
			+ "GROUP BY m.product.productName, m.movementType.movementType, m.movementType.movementTypeId")
	List<Object[]> getMovementsSummary(@Param("startDate") LocalDateTime startDate,
			@Param("endDate") LocalDateTime endDate, @Param("warehouseId") Long warehouseId);
	
	@Query("SELECT " +
	           "p.productId, p.productName, " +
	           "SUM(CASE WHEN mt.movementTypeId = 1 THEN m.quantity ELSE 0 END) AS totalRecepcion, " + // Recepción
	           "SUM(CASE WHEN mt.movementTypeId = 2 THEN m.quantity ELSE 0 END) AS totalDespacho, " +  // Despacho
	           "SUM(CASE WHEN mt.movementTypeId = 3 THEN m.quantity ELSE 0 END) AS totalMerma, " +     // Merma
	           "SUM(CASE WHEN mt.movementTypeId = 4 THEN m.quantity ELSE 0 END) AS totalInvInicial " +  // Inventario Inicial
	           "FROM Movement m " +
	           "JOIN m.product p " +
	           "JOIN m.movementType mt " +
	           "WHERE m.movementDate BETWEEN :startDate AND :endDate " +
	           "AND m.warehouseId = :warehouseId " +
	           "AND mt.movementTypeId IN :movementTypeIds " +
	           "GROUP BY p.productId, p.productName")
	    List<Object[]> getMovementsSummaryGroupedByProduct(
	            @Param("startDate") LocalDateTime startDate,
	            @Param("endDate") LocalDateTime endDate,
	            @Param("warehouseId") Long warehouseId,
	            @Param("movementTypeIds") List<Long> movementTypeIds);
	    
	    @Query("SELECT " +
	            "p.productId, p.productName, " +
	            "SUM(CASE WHEN mt.movementTypeId = 1 THEN m.quantity ELSE 0 END) AS totalRecepcion, " +
	            "SUM(CASE WHEN mt.movementTypeId = 2 THEN m.quantity ELSE 0 END) AS totalDespacho, " +
	            "SUM(CASE WHEN mt.movementTypeId = 3 THEN m.quantity ELSE 0 END) AS totalMerma, " +
	            "SUM(CASE WHEN mt.movementTypeId = 4 THEN m.quantity ELSE 0 END) AS totalInvInicial, " +
	            "SUM(CASE WHEN mt.movementTypeId = 1 THEN m.quantity ELSE 0 END) + " + // Suma Recepción
	            "SUM(CASE WHEN mt.movementTypeId = 4 THEN m.quantity ELSE 0 END) - " + // Suma Inventario Inicial
	            "SUM(CASE WHEN mt.movementTypeId = 2 THEN m.quantity ELSE 0 END) - " + // Resta Despacho
	            "SUM(CASE WHEN mt.movementTypeId = 3 THEN m.quantity ELSE 0 END) AS totalInvFinal " + // Resta Merma
	            "FROM Movement m " +
	            "LEFT JOIN m.product p " +
	            "LEFT JOIN m.movementType mt " +
	            "WHERE m.movementDate BETWEEN :startDate AND :endDate " +
	            "AND m.warehouseId = :warehouseId " +
	            "AND mt.movementTypeId IN :movementTypeIds " +
	            "GROUP BY p.productId, p.productName")
	     List<Object[]> getMovementsSummaryGroupedByProductWithInvFinal(
	             @Param("startDate") LocalDateTime startDate,
	             @Param("endDate") LocalDateTime endDate,
	             @Param("warehouseId") Long warehouseId,
	             @Param("movementTypeIds") List<Long> movementTypeIds);
	          
	     @Query("SELECT " +
	    	       "p.productId, p.productName, " +
	    	       "COALESCE(SUM(CASE WHEN mt.movementTypeId = 1 THEN m.quantity ELSE 0 END), 0) AS totalRecepcion, " + // Recepción
	    	       "COALESCE(SUM(CASE WHEN mt.movementTypeId = 2 THEN m.quantity ELSE 0 END), 0) AS totalDespacho, " +  // Despacho
	    	       "COALESCE(SUM(CASE WHEN mt.movementTypeId = 3 THEN m.quantity ELSE 0 END), 0) AS totalMerma, " +     // Merma
	    	       "COALESCE(SUM(CASE WHEN mt.movementTypeId = 4 THEN m.quantity ELSE 0 END), 0) AS totalInvInicial, " +  // Inventario Inicial
	    	       "COALESCE(SUM(CASE WHEN mt.movementTypeId = 1 THEN m.quantity ELSE 0 END), 0) + " + // Suma Recepción
	    	       "COALESCE(SUM(CASE WHEN mt.movementTypeId = 4 THEN m.quantity ELSE 0 END), 0) - " + // Suma Inventario Inicial
	    	       "COALESCE(SUM(CASE WHEN mt.movementTypeId = 2 THEN m.quantity ELSE 0 END), 0) - " + // Resta Despacho
	    	       "COALESCE(SUM(CASE WHEN mt.movementTypeId = 3 THEN m.quantity ELSE 0 END), 0) AS totalInvFinal " + // Resta Merma
	    	       "FROM Product p " + // Partimos de la tabla de productos
	    	       "LEFT JOIN Movement m ON p.productId = m.product.productId " + // LEFT JOIN con Movement
	    	       "LEFT JOIN m.movementType mt " + // LEFT JOIN con MovementType
	    	       "WHERE (m.movementDate BETWEEN :startDate AND :endDate OR m.movementDate IS NULL) " + // Filtro de fechas
	    	       "AND (m.warehouseId = :warehouseId OR m.warehouseId IS NULL) " + // Filtro de almacén
	    	       "AND (mt.movementTypeId IN :movementTypeIds OR mt.movementTypeId IS NULL) " + // Filtro de tipos de movimiento
	    	       "GROUP BY p.productId, p.productName")
	    	List<Object[]> getMovementsSummaryGroupedByProductWithInvFinalWithAllProducts(
	    	        @Param("startDate") LocalDateTime startDate,
	    	        @Param("endDate") LocalDateTime endDate,
	    	        @Param("warehouseId") Long warehouseId,
	    	        @Param("movementTypeIds") List<Long> movementTypeIds);

	    	@Query("SELECT " +
	    		       "p.productId, p.productName, " +
	    		       "COALESCE(SUM(CASE WHEN mt.movementTypeId = :movementTypeId THEN m.quantity ELSE 0 END), 0) AS totalMovimiento, " +
	    		       "COALESCE(SUM(CASE WHEN mt.movementTypeId = :movementTypeId AND EXTRACT(WEEK FROM CAST(m.movementDate AS DATE)) = EXTRACT(WEEK FROM CAST(:fechaInicio AS DATE)) THEN m.quantity ELSE 0 END), 0) AS semana1, " +
	    		       "COALESCE(SUM(CASE WHEN mt.movementTypeId = :movementTypeId AND EXTRACT(WEEK FROM CAST(m.movementDate AS DATE)) = EXTRACT(WEEK FROM CAST(:fechaInicio AS DATE)) + 1 THEN m.quantity ELSE 0 END), 0) AS semana2, " +
	    		       "COALESCE(SUM(CASE WHEN mt.movementTypeId = :movementTypeId AND EXTRACT(WEEK FROM CAST(m.movementDate AS DATE)) = EXTRACT(WEEK FROM CAST(:fechaInicio AS DATE)) + 2 THEN m.quantity ELSE 0 END), 0) AS semana3, " +
	    		       "COALESCE(SUM(CASE WHEN mt.movementTypeId = :movementTypeId AND EXTRACT(WEEK FROM CAST(m.movementDate AS DATE)) = EXTRACT(WEEK FROM CAST(:fechaInicio AS DATE)) + 3 THEN m.quantity ELSE 0 END), 0) AS semana4, " +
	    		       "COALESCE(SUM(CASE WHEN mt.movementTypeId = :movementTypeId AND EXTRACT(WEEK FROM CAST(m.movementDate AS DATE)) = EXTRACT(WEEK FROM CAST(:fechaInicio AS DATE)) + 4 THEN m.quantity ELSE 0 END), 0) AS semana5 " +
	    		       "FROM Product p " +
	    		       "LEFT JOIN Movement m ON m.product = p AND m.movementDate BETWEEN :fechaInicio AND :fechaFin AND m.movementType.movementTypeId = :movementTypeId " + // Corrected line
	    		       "LEFT JOIN MovementType mt ON m.movementType = mt " +
	    		       "WHERE (:warehouseId IS NULL OR m.warehouseId = :warehouseId) " +
	    		       "GROUP BY p.productId, p.productName " +
	    		       "ORDER BY p.productName")
	    		List<Object[]> findMovementsSummaryByMovementTypeAndWeekly(
	    		       @Param("fechaInicio") LocalDateTime fechaInicio,
	    		       @Param("fechaFin") LocalDateTime fechaFin,
	    		       @Param("warehouseId") Long warehouseId,
	    		       @Param("movementTypeId") Long movementTypeId);
}