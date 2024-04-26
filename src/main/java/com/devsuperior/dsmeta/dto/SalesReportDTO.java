package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.projections.ReportProjection;

public class SalesReportDTO {
	private String sellerName; // Nome do vendedor
    private LocalDate saleDate; // Data da venda
    private double amount; // Valor da venda
    

    // Construtor
    public SalesReportDTO(String sellerName, LocalDate saleDate, double amount) {
        this.sellerName = sellerName;
        this.saleDate = saleDate;
        this.amount = amount;
        
    }
    
    public SalesReportDTO(ReportProjection projection) {
        this.sellerName = projection.getSellerName();
        this.saleDate = projection.getDate();
        this.amount = projection.getAmount();
        
    }

    // Getters e setters
    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
