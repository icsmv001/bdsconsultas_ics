//#############################################################
//## Aula - Devsuperior
//## URI2611 - spring boot SQL e JPQL
//## Finalizado com sucesso.
//#############################################################

package com.devsuperior.uri2611.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.entities.Movie;
import com.devsuperior.uri2611.projections.MovieMinProjection;

public interface MovieRepository extends JpaRepository<Movie,Long>  {

	
	//consulta usando query nativa sql
	@Query(nativeQuery = true,value="select m.id,m.name "
			+ "FROM movies m "
			+ "INNER JOIN genres g ON (m.id_genres = g.id) "
			+ "WHERE 0=0 "
			+ "AND upper(g.description) = upper(:genreName)")
	List<MovieMinProjection> search1(String genreName);
	
	
	// consulta usando jpql
	@Query("SELECT new com.devsuperior.uri2611.dto.MovieMinDTO(obj.id, obj.name) "
			+ "FROM Movie obj "
			+ "WHERE upper(obj.genre.description) = upper(:genreName)" )
	 List<MovieMinDTO> search2(String genreName);
		
	
	
}
