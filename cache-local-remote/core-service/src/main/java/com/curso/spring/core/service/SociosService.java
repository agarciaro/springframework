package com.curso.spring.core.service;

import java.util.List;

import com.curso.spring.core.model.SocioDto;
import com.curso.spring.core.model.entity.Socio;

public interface SociosService {
	
	public List<Socio> findAll();

	List<SocioDto> findAllMock();
	
}
