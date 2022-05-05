package com.curso.spring.socios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.spring.core.model.QueryParams;
import com.curso.spring.core.model.Socio;
import com.curso.spring.core.service.SociosService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/socios")
public class SociosRestController {
	
	@Autowired
	QueryParams queryParams;
	
	@Autowired
	SociosService sociosService;
	
	@Operation(summary = "Devuelve los socios de la Base de Datos con un límite por defecto de 10")
	@GetMapping
	public List<Socio> findByFilter(@RequestParam(required = false) Integer limit) {
		queryParams.setLimit(limit);

		return sociosService.findAll();
	}
	
}
