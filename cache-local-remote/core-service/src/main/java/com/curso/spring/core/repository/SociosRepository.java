package com.curso.spring.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.curso.spring.core.model.entity.Socio;

@Repository
public interface SociosRepository extends JpaRepository<Socio, Long> {
	
	
	public List<Socio> findByNombre(String nombre);
	
	@Query( value = "from Socio s where s.nombre = :nombre", nativeQuery = false)
	public List<Socio> findByNombreCustom(@Param("nombre") String nombre);
	
}
