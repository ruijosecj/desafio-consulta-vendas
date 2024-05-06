package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SalesSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	 public Page<SalesReportDTO> getReport(String minDateSTR, String maxDateSTR, String name, Pageable pageable) {
		 LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		 LocalDate max = maxDateSTR.equals("") ? today : LocalDate.parse(maxDateSTR);
		 LocalDate min = minDateSTR.equals("") ? max.minusYears(1L) : LocalDate.parse(minDateSTR);
	 
		 Page<Sale> result = repository.searchSales(min, max, name, pageable);
		
		 return  result.map(x -> new SalesReportDTO(x));
	 }
	 
	 public List<SalesSummaryDTO> getSummary(String minDateSTR, String maxDateSTR) {
		 LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		 LocalDate max = maxDateSTR.equals("") ? today : LocalDate.parse(maxDateSTR);
		 LocalDate min = minDateSTR.equals("") ? max.minusYears(1L) : LocalDate.parse(minDateSTR);
	 
		 List<SalesSummaryDTO> result = repository.searchSummary(min, max);
		
		 return  result;
	 }
	    
}
