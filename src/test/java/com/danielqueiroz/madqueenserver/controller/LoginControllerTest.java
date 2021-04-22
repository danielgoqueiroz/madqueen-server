package com.danielqueiroz.madqueenserver.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import com.danielqueiroz.madqueenserver.jwt.UsernameAndPasswordAuthenticationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public class LoginControllerTest extends BaseControllerTest{
	
	@Test
	public void shoulDoValidLogin() throws Exception {

		String token = createDefaultUser();
		assertNotNull(token);
		assertTrue(token.contains("Bearer "));

	}
	
	@Test
	public void shouldNotGetUserByWithoutAuthentication() throws JsonProcessingException, Exception {
		
		UsernameAndPasswordAuthenticationRequest user = new UsernameAndPasswordAuthenticationRequest("Usuário inexistente", "senha645987");
		
		mockMvc.perform(post("/login").content(helper.getObjectMapper().writeValueAsString(user)))
				.andExpect(status().is(403));
				
	}

}
