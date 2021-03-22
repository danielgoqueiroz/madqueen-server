package com.danielqueiroz.madqueenserver.jwt;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private final AuthenticationManager authenticationManager;
	
	public JwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
		try {
		UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper().readValue(
				request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), 
				authenticationRequest.getPassword()
				);
		
				Authentication authenticate = this.authenticationManager.authenticate(authentication);
				
				return authenticate;
				
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		Date now = new Date();
		String key = "McQfTjWnZr4u7w!z%C*F-JaNdRgUkXp2s5v8y/A?D(G+KbPeShVmYq3t6w9z$C&E";
		
		String token = Jwts.builder()
			.setSubject(authResult.getName())
			.claim("authorities", authResult.getAuthorities())
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + 60000 * 6))
			.signWith(Keys.hmacShaKeyFor(key.getBytes()))
			.compact();
		
		response.addHeader("Authorization", "Bearer " + token);
		
	}
}
