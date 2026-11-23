package com.devsuperior.uri2990.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.entities.Empregado;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;

public interface EmpregadoRepository extends JpaRepository<Empregado, String> {

	
	
	//consulta usando query nativa sql
			@Query(nativeQuery = true,value="select "
					+ "   e.cpf, e.enome,d.dnome "
					+ "from departamentos d "
					+ "inner join empregados e on (d.dnumero = e.dnumero) "
					+ "left  join trabalha   t on (e.cpf=t.cpf_emp ) "
					+ "where t.cpf_emp is null "
					+ "order by e.cpf")
			
			List<EmpregadoDeptProjection> search1();
		
	
	// consulta usando query usando jsql
			@Query("SELECT new com.devsuperior.uri2990.dto.EmpregadoDeptDTO(obj.cpf, obj.enome, obj.departamento.dnome) "
					+ "from Empregado  obj "
					+ "where obj.cpf NOT IN ( "
					+ "SELECT obj.cpf "
					+ "FROM Empregado obj "
					+ "INNER JOIN obj.projetosOndeTrabalha "
					+ ") "
					+ "ORDER BY obj.cpf ")
			
			List<EmpregadoDeptDTO> search2();
}
