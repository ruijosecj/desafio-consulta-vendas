package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

}
