package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.ReportProjection;
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
	
	 public List<SalesReportDTO> getReport(String minDateSTR, String maxDateSTR, String name, Pageable pageable) {
		 LocalDate minDate = LocalDate.parse(minDateSTR);
		 LocalDate maxDate = LocalDate.parse(maxDateSTR);
	 
		 if (minDate == null && maxDate == null) {
		        LocalDate currentDate = LocalDate.now();
		        minDate = currentDate.minusMonths(12);
		        maxDate = currentDate;
		    }else if (minDate == null) {
		    	minDate = maxDate.minusYears(1L);
		    }else if (maxDate == null) {
		    		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		    		maxDate = today;
		    }
		 List<ReportProjection> list = repository.findSalesByDateRangeAndSeller(minDate, maxDate, name);
		 List<SalesReportDTO> result = list.stream().map(x -> new SalesReportDTO(x)).collect(Collectors.toList());
		 
		 for(SalesReportDTO obj: result) {
			 System.out.println(obj);
		 }

		 return  result;
	 }
	    
}
