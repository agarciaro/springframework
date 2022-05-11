package com.curso.spring.core.repository;

import java.util.List;

import com.curso.spring.core.model.entity.Socio;

public interface SociosJdbcRepository {
	public List<Socio> findAll();
	public List<Socio> findByNombre(String nombre);
	public Socio insert(Socio socio);
}
