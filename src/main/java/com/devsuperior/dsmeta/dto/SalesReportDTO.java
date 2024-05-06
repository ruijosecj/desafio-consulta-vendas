package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;

public class SalesReportDTO {
	private Long id;
	private String sellerName; // Nome do vendedor
    private LocalDate date; // Data da venda
    private double amount; // Valor da venda
    
    public SalesReportDTO() {}
        
    public SalesReportDTO(Long id, String sellerName, LocalDate date, double amount) {
		super();
		this.id = id;
		this.sellerName = sellerName;
		this.date = date;
		this.amount = amount;
	}

    
    public SalesReportDTO(Sale entity) {
    	this.id = entity.getId();
        this.sellerName = entity.getSeller().getName();
        this.date = entity.getDate();
        this.amount = entity.getAmount();
        
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
