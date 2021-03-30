package com.danielqueiroz.madqueenserver.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.base.Strings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JwtTokenVerifier extends OncePerRequestFilter {

	private final JwtConfig jwtConfig;
	private final SecretKey secretKey;
	
	@Autowired
	public JwtTokenVerifier(JwtConfig jwtConfig, SecretKey secretKey) {
		this.jwtConfig = jwtConfig;
		this.secretKey = secretKey;
	}
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain) throws ServletException, IOException {
		
		String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());
		
		if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			filterChain.doFilter(request, response);
			return;
		}
		
		String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");

		try {
			Jws<Claims> claimsJws = Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token);
			Claims body = claimsJws.getBody();
			
			String username = body.getSubject();
			
			List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
			
			List<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream().map(m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toList());
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(
					username, 
					null,
					simpleGrantedAuthorities);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			
		} catch (JwtException e) {
			throw new IllegalStateException(String.format("O token %s não é confiável.", token));
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
	            "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
		filterChain.doFilter(request, response);
	}

}
