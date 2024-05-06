package com.devsuperior.dsmeta.dto;

public class SalesSummaryDTO {
	private String name;
	private Double totalVendas;
	
	public SalesSummaryDTO() {}

	public SalesSummaryDTO(String name, Double totalVendas) {
		this.name = name;
		this.totalVendas = totalVendas;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTotalVendas() {
		return totalVendas;
	}

	public void setTotalVendas(Double totalVendas) {
		this.totalVendas = totalVendas;
	}
	
	
}
