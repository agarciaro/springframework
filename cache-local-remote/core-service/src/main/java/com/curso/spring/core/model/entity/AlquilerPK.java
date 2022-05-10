package com.curso.spring.core.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AlquilerPK implements Serializable {

	private static final long serialVersionUID = 5143271434944832380L;
	
	private Long idEjemplar;
	private Long idSocio;
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime fechaInicio;
}
