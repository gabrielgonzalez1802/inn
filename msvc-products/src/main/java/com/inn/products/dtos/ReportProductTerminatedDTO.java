package com.inn.products.dtos;

import java.util.List;

public class ReportProductTerminatedDTO {
	
	private String almacen;
    private String fechaInicio;
    private String fechaFin;
    private String semana;
    
    private List<ProductTerminatedDTO> productos;
    
    public ReportProductTerminatedDTO() {
	}
    
    public String getAlmacen() {
		return almacen;
	}

	public void setAlmacen(String almacen) {
		this.almacen = almacen;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<ProductTerminatedDTO> getProductos() {
		return productos;
	}


	public void setProductos(List<ProductTerminatedDTO> productos) {
		this.productos = productos;
	}

	public String getSemana() {
		return semana;
	}

	public void setSemana(String semana) {
		this.semana = semana;
	}
}