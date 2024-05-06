package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dsmeta.dto.SalesSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.ReportProjection;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@Query(nativeQuery = true, value="SELECT sl.id, sl.date, sl.amount, se.name"
			+ " FROM tb_sales as sl "
			+ "INNER JOIN tb_seller as se ON tb_sales.seller_id = seller.id "
			+ "WHERE tb_sales.date BETWEEN :minDate AND :maxDate AND UPPER(sel.name) LIKE :name")
    List<ReportProjection> findSalesByDateRangeAndSeller(@Param("minDate") LocalDate minDate,
                                             @Param("maxDate") LocalDate maxDate,
                                             @Param("name") String name);
	
	@Query(value = "SELECT obj FROM Sale obj JOIN FETCH obj.seller"
			+ " WHERE obj.date >= :min "
			+ "AND obj.date <= :max "
			+ "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))",
			countQuery = "SELECT COUNT(obj) FROM Sale obj JOIN obj.seller "
					+ "WHERE obj.date >= :min "
					+ "AND obj.date <= :max "
					+ "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))")
	Page <Sale> searchSales(LocalDate min, LocalDate max, String name, Pageable pageable);
	
	
	@Query(value = "SELECT new com.devsuperior.dsmeta.dto.SalesSummaryDTO(obj.seller.name, SUM(obj.amount)) "
			+ "FROM Sale obj "
			+ "GROUP BY obj.seller.name")
	List <SalesSummaryDTO> searchSummary(LocalDate min, LocalDate max);
}
