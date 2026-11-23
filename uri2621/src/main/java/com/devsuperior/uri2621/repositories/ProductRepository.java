package com.devsuperior.uri2621.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.entities.Product;
import com.devsuperior.uri2621.projections.ProductMinProjection;

public interface ProductRepository extends JpaRepository<Product, Long> {

	
	
	//consulta usando query nativa sql
		@Query(nativeQuery = true,value="SELECT prd.name "
				+ "FROM products prd "
				+ "INNER JOIN providers prv ON (prd.id_providers = prv.id) "
				+ "WHERE 0=0  "
				+ "AND prd.amount between :min and :max "
				+ "AND upper(prv.name) LIKE upper(CONCAT(:beginName,'%')) ")
				
				List<ProductMinProjection> search1(Integer min, Integer max, String beginName);
				
				
	
		// consulta usando jpql
		@Query("SELECT new com.devsuperior.uri2621.dto.ProductMinDTO(prd.name) "
				+ "FROM Product prd "
				+ "WHERE 0=0  "
				+ "AND prd.amount between :min and :max "
				+ "AND upper(prd.provider.name) LIKE upper(CONCAT(:beginName,'%')) ")
				
				List<ProductMinDTO> search2(Integer min, Integer max, String beginName);
				
	
	
	
	
	
	
}
