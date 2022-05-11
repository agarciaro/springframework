package com.curso.spring.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.spring.core.model.entity.Usuario;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Long>{
	public Usuario findByUserName(String userName);
}
