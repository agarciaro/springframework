package com.curso.spring.core.service;

import java.util.List;

import com.curso.spring.core.model.SocioDto;

public interface SociosService {
	
	public List<SocioDto> findAll();

	List<SocioDto> findAllMock();
	
}
