package com.inn.products.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.inn.products.config.RequiresRoles;
import com.inn.products.dtos.ProductMovementSummaryDTO;
import com.inn.products.dtos.ProductMovementWeeklyDTO;
import com.inn.products.services.MovementService;
import com.inn.products.services.ReportService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/products/reports")
public class ReportController {

    @Autowired
    private ReportService reporteService;
    
    @Autowired
    private MovementService movementService;

    @GetMapping("/weekly")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<?> generarReporteSemanal(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin,
            @RequestParam(value = "warehouseId", defaultValue = "1") Long warehouseId,
            @RequestParam(value = "movementTypeIds", defaultValue = "1,2,3,4") List<Long> movementTypeIds,
            @RequestParam(value = "semana", defaultValue = "1") Integer semana,
            @RequestParam(value = "formato", defaultValue = "PDF") String formato,
            HttpServletResponse response
    ) throws Exception {

        try {
        	if(movementService.fechaNoExcedeSemana(fechaInicio, fechaFin)) {
	            // 1. Obtener los movimientos
	            List<ProductMovementSummaryDTO> movements = movementService.findMovementsGroupedByProductName(fechaInicio, fechaFin, warehouseId, movementTypeIds);
	
	            byte[] reporte;
	            HttpHeaders headers = new HttpHeaders();
	            
	            // 2. Generar el reporte
	            if(formato.equalsIgnoreCase("PDF")) {
		            reporte = reporteService.generarReporteSemanalPDF(movements, fechaInicio, fechaFin, semana, warehouseId);
		            headers.setContentType(MediaType.APPLICATION_PDF);
		            headers.setContentDispositionFormData("attachment", "reporteSemanal.pdf");
		            headers.setContentLength(reporte.length);
	            }else {
		           reporte = reporteService.generarReporteSemanalXLSX(movements, fechaInicio, fechaFin, semana,warehouseId);

		        // 3. Forzar la descarga del archivo
	                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); // Tipo de contenido XLSX
	                response.setHeader("Content-Disposition", "attachment; filename=reporteSemanal.xlsx");
	                response.setContentLength(reporte.length);
	                response.getOutputStream().write(reporte);
	                response.getOutputStream().flush();

	                return new ResponseEntity<>(HttpStatus.OK);
	            }
	
	            // 3. Devolver el reporte en la respuesta
	            return new ResponseEntity<>(reporte, headers, HttpStatus.OK);
        	}else {
                return ResponseEntity.badRequest().body("La fecha no debe exceder de una semana");
        	}
        } catch (MethodArgumentTypeMismatchException ex) {
            String parameterName = ex.getParameter().getParameterName();
            String expectedType = ex.getRequiredType().getSimpleName();
            String errorMessage = String.format("El parámetro '%s' debe ser de tipo '%s'.", parameterName, expectedType);
            return ResponseEntity.badRequest().body(errorMessage.getBytes());

        } catch (Exception ex) {
            // Manejo de otras excepciones (ej. excepciones de la capa de servicio)
            return ResponseEntity.internalServerError().body("Error interno del servidor: " + ex.getMessage());
        }
    }
    
    @GetMapping("/monthly")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<?> generarReporteMensual(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin,
            @RequestParam(value = "warehouseId", defaultValue = "1") Long warehouseId,
            @RequestParam(value = "movementTypeId", defaultValue = "1") Long movementTypeId,
            @RequestParam(value = "formato", defaultValue = "PDF") String formato,
            HttpServletResponse response
    ) throws Exception {

        try {
        	if(movementService.fechaNoExcedeMes(fechaInicio, fechaFin)) {
	            // 1. Obtener los movimientos
	            List<ProductMovementWeeklyDTO> movements = movementService.findMovementsMonthsGroupedByProductName(fechaInicio, fechaFin, warehouseId, movementTypeId);
	
	            byte[] reporte;
	            HttpHeaders headers = new HttpHeaders();
	            
	            // 2. Generar el reporte
	            if(formato.equalsIgnoreCase("PDF")) {
		            reporte = reporteService.generarReporteMonthlyPDF(movements, fechaInicio, fechaFin, movementTypeId, warehouseId);
		            
		            if(movementTypeId.equals(1l)) {
			            headers.setContentDispositionFormData("attachment", "reporteMensualRecepcion.pdf");
		            }else if(movementTypeId.equals(2l)) {
			            headers.setContentDispositionFormData("attachment", "reporteMensualDespacho.pdf");
		            }else if(movementTypeId.equals(3l)) {
			            headers.setContentDispositionFormData("attachment", "reporteMensualMerma.pdf");
		            }else {
		                return ResponseEntity.badRequest().body("El tipo de movimiento no es válido");
		            }
		            
		            headers.setContentType(MediaType.APPLICATION_PDF);
		            headers.setContentLength(reporte.length);
	            }else {
		           reporte = reporteService.generarReporteMonthlyXLSX(movements, fechaInicio, fechaFin, movementTypeId, warehouseId);

		        // 3. Forzar la descarga del archivo
	                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); // Tipo de contenido XLSX
	               
		            if(movementTypeId.equals(1l)) {
		                response.setHeader("Content-Disposition", "attachment; filename=reporteMensualRecepcion.xlsx");
		            }else if(movementTypeId.equals(2l)) {
		                response.setHeader("Content-Disposition", "attachment; filename=reporteMensualDespacho.xlsx");
		            }else if(movementTypeId.equals(3l)) {
		                response.setHeader("Content-Disposition", "attachment; filename=reporteMensualMerma.xlsx");
		            }else {
		                return ResponseEntity.badRequest().body("El tipo de movimiento no es válido");
		            }
	                
	                response.setContentLength(reporte.length);
	                response.getOutputStream().write(reporte);
	                response.getOutputStream().flush();

	                return new ResponseEntity<>(HttpStatus.OK);
	            }
	
	            // 3. Devolver el reporte en la respuesta
	            return new ResponseEntity<>(reporte, headers, HttpStatus.OK);
        	}else {
                return ResponseEntity.badRequest().body("La fecha no debe exceder de un mes");
        	}
        } catch (MethodArgumentTypeMismatchException ex) {
            String parameterName = ex.getParameter().getParameterName();
            String expectedType = ex.getRequiredType().getSimpleName();
            String errorMessage = String.format("El parámetro '%s' debe ser de tipo '%s'.", parameterName, expectedType);
            return ResponseEntity.badRequest().body(errorMessage.getBytes());

        } catch (Exception ex) {
            // Manejo de otras excepciones (ej. excepciones de la capa de servicio)
            return ResponseEntity.internalServerError().body("Error interno del servidor: " + ex.getMessage());
        }
    }
    
    @GetMapping("/monthly_end")
    @RequiresRoles({"ROLE_ADMIN"})
    public ResponseEntity<?> generarReporteCierreMes(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin,
            @RequestParam(value = "warehouseId", defaultValue = "1") Long warehouseId,
            @RequestParam(value = "movementTypeIds", defaultValue = "1,2,3,4") List<Long> movementTypeIds,
            @RequestParam(value = "semana", defaultValue = "1") Integer semana,
            @RequestParam(value = "formato", defaultValue = "PDF") String formato,
            HttpServletResponse response
    ) throws Exception {

        try {
        	if(movementService.fechaNoExcedeMes(fechaInicio, fechaFin)) {
	            // 1. Obtener los movimientos
	            List<ProductMovementSummaryDTO> movements = movementService.findMovementsGroupedByProductName(fechaInicio, fechaFin, warehouseId, movementTypeIds);
	
	            byte[] reporte;
	            HttpHeaders headers = new HttpHeaders();
	            
	            // 2. Generar el reporte
	            if(formato.equalsIgnoreCase("PDF")) {
		            reporte = reporteService.generarReporteFinMesPDF(movements, fechaInicio, fechaFin, semana, warehouseId);
		            headers.setContentType(MediaType.APPLICATION_PDF);
		            headers.setContentDispositionFormData("attachment", "reporteCierreMes.pdf");
		            headers.setContentLength(reporte.length);
	            }else {
		           reporte = reporteService.generarReporteFinMesXLSX(movements, fechaInicio, fechaFin, semana, warehouseId);

		        // 3. Forzar la descarga del archivo
	                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); // Tipo de contenido XLSX
	                response.setHeader("Content-Disposition", "attachment; filename=reporteCierreMes.xlsx");
	                response.setContentLength(reporte.length);
	                response.getOutputStream().write(reporte);
	                response.getOutputStream().flush();

	                return new ResponseEntity<>(HttpStatus.OK);
	            }
	
	            // 3. Devolver el reporte en la respuesta
	            return new ResponseEntity<>(reporte, headers, HttpStatus.OK);
        	}else {
                return ResponseEntity.badRequest().body("La fecha no debe exceder de un mes");
        	}
        } catch (MethodArgumentTypeMismatchException ex) {
            String parameterName = ex.getParameter().getParameterName();
            String expectedType = ex.getRequiredType().getSimpleName();
            String errorMessage = String.format("El parámetro '%s' debe ser de tipo '%s'.", parameterName, expectedType);
            return ResponseEntity.badRequest().body(errorMessage.getBytes());

        } catch (Exception ex) {
            // Manejo de otras excepciones (ej. excepciones de la capa de servicio)
            return ResponseEntity.internalServerError().body("Error interno del servidor: " + ex.getMessage());
        }
    }
}