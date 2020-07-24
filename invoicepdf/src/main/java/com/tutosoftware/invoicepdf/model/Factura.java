package com.tutosoftware.invoicepdf.model;

import java.util.Date;

public class Factura {
	
	private int cantidad;
	private Date fechaServicio;	
	private String concepto;
	private double precioUnitario;
	private double descuento;
	private double total;
	
	public Factura(int cantidad,Date fechaServicio, String concepto, double precioUnitario,double descuento, double total) {
		super();
		this.cantidad = cantidad;
		this.fechaServicio = fechaServicio;
		this.concepto = concepto;
		this.precioUnitario = precioUnitario;
		this.descuento = descuento;
		this.total = total;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public Date getFechaServicio() {
		return fechaServicio;
	}
	public void setFechaServicio(Date fechaServicio) {
		this.fechaServicio = fechaServicio;
	}
	
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

}
