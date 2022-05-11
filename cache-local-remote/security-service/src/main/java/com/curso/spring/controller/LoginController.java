package com.curso.spring.controller;

import javax.security.auth.message.AuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.spring.core.model.entity.Usuario;
import com.curso.spring.core.repository.UsuariosRepository;
import com.curso.spring.service.JwtTokenUtil;

@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	UsuariosRepository usuariosRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@PostMapping("/login")
	public String login(@RequestParam(name = "username", required = true) String username, @RequestParam(name = "password", required = true) String password) throws AuthException {
//		Usuario usuario = usuariosRepository.findByUserName(username);
//		if(usuario == null || usuario.getPassword().equals(password)) {
//			throw new AuthException("Usuario o password incorrectos");
//		}
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		
		String token = jwtTokenUtil.generateToken(userDetailsService.loadUserByUsername(username));
		
		return token;
	}
	
}
