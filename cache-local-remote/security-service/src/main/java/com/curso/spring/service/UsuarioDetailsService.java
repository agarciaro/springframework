package com.curso.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.curso.spring.core.model.entity.Usuario;
import com.curso.spring.core.repository.UsuariosRepository;
import com.curso.spring.model.UsuarioDetails;

@Service
public class UsuarioDetailsService implements UserDetailsService {
	
	@Autowired
	UsuariosRepository usuariosRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuariosRepository.findByUserName(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("El usuario " + username + " no se ha encontrado");
		}
		
		return new UsuarioDetails(usuario);
	}

}
