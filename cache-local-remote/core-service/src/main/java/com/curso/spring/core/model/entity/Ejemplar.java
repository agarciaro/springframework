package com.curso.spring.core.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ejemplar {
	
	private Long id;
	private String estadoConservacion;
	

	private Long idPelicula;
	
}
