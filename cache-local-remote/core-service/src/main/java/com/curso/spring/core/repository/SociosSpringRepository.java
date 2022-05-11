package com.curso.spring.core.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.curso.spring.core.model.entity.Socio;

@Repository
public interface SociosSpringRepository extends CrudRepository<Socio, Long> {
	
	
	public List<Socio> findByNombre(String nombre);
	
	@Query("select * from socio s where s.nombre = :nombre")
	public List<Socio> findByNombreCustom(@Param("nombre") String nombre);
	
}
