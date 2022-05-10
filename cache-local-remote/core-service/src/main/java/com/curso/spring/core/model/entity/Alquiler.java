package com.curso.spring.core.model.entity;

import java.time.LocalDateTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Alquiler {
	@EmbeddedId
	private AlquilerPK id;
	
	@ManyToOne
	@JoinColumn(name = "id_ejemplar")
	@MapsId("idEjemplar")
	private Ejemplar ejemplar;
	
	@ManyToOne
	@JoinColumn(name = "id_socio")
	@MapsId("idSocio")
	private Socio socio;
	
	private LocalDateTime fechaDevolucion;
}
