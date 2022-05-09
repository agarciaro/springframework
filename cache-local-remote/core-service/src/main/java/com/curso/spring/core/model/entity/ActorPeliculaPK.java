package com.curso.spring.core.model.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ActorPeliculaPK implements Serializable {
	
	private static final long serialVersionUID = 9175561862113007943L;
	
	private Long idActor;
	private Long idPelicula;
	
}
