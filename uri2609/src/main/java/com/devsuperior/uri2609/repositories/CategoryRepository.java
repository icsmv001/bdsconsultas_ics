package com.devsuperior.uri2609.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.entities.Category;
import com.devsuperior.uri2609.projections.CategorySumProjection;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	
	
	//consulta usando query nativa sql
		@Query(nativeQuery = true,value="SELECT  ctg.name,  sum(prd.amount) "
				+ "FROM  categories ctg "
				+ "INNER JOIN products prd on (prd.id_categories = ctg.id) "
				+ "GROUP BY   ctg.name ")
		
		List<CategorySumProjection> search1();
		
		
	
		//consulta usando query nativa sql
				@Query("SELECT new com.devsuperior.uri2609.dto.CategorySumDTO (obj.category.name,  sum(obj.amount)) "
						+ "FROM  Product obj "
			    		+ "GROUP BY   obj.category.name ")				
				List<CategorySumDTO> search2();
				
				
	
	
	
}
