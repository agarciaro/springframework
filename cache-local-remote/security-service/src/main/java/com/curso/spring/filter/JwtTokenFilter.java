package com.curso.spring.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.curso.spring.core.model.JwtToken;
import com.curso.spring.service.JwtTokenUtil;
import com.curso.spring.service.UsuarioDetailsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter{
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	UsuarioDetailsService usuarioDetailsService;
	
	@Autowired
	JwtToken jwtToken;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			log.warn("No se ha podido extraer el token");
			filterChain.doFilter(request, response);
			return;
		}
		
		final String token = authHeader.split(" ")[1].trim(); // Separamos el token de la petición
		
		final String username = jwtTokenUtil.getUsernameFromToken(token);
		
		jwtToken.setToken(token);
		jwtToken.setUsername(username);
		
		UserDetails userDetails = usuarioDetailsService.loadUserByUsername(username);
		
		if(jwtTokenUtil.validateToken(token, userDetails)) {
			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		
		filterChain.doFilter(request, response);
		
	}

}
