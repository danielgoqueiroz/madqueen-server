package com.danielqueiroz.madqueenserver.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LoginControllerTest extends BaseControllerTest{
	
	@Test
	public void shouldNotGetUserByWithoutAuthentication() {
		
		String token = getToken("usuerio inexistente", "123");
		
		assertNotNull(token);
		assertTrue(token.contains("Bearer "), "Não foi encontrado o token com 'Bearer '");
		
	}

}
