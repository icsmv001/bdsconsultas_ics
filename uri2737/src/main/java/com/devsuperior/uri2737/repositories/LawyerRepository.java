package com.devsuperior.uri2737.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2737.entities.Lawyer;
import com.devsuperior.uri2737.projections.LawyerMinProjection;

public interface LawyerRepository extends JpaRepository<Lawyer, Long> {

	//consulta usando query nativa sql
		@Query(nativeQuery = true,value="select l.name , l.customers_number as customersnumber "
				+ "from lawyers l "
				+ "where  l.customers_number = (select max(l.customers_number)from lawyers l ) "
				+ "union all "
				+ "select l.name , l.customers_number "
				+ "from lawyers l "
				+ "where  l.customers_number = (select min(l.customers_number)from lawyers l ) "
				+ "union all "
				+ "select 'Average' , ROUND(avg(l.customers_number),0) as media "
				+ "from lawyers l")
		
		List<LawyerMinProjection> search1();
	
	
	
	
	
	
	
	
	
	
	
}
