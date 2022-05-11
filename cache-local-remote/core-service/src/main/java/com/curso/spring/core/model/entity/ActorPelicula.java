package com.curso.spring.core.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorPelicula {
	
	private ActorPeliculaPK id;
	
	private String papel;

	private Actor actor;
	
	private Pelicula pelicula;
}
