package com.danielqueiroz.madqueenserver.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.danielqueiroz.madqueenserver.jwt.UsernameAndPasswordAuthenticationRequest;

public class UserControllerTest extends BaseControllerTest {

	@Test
	public void shouldGetuserByUsernameWithAuthentication() {
		
		String token = getToken("usuarioteste", "senhateste");
		
		assertNotNull(token);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);

		ResponseEntity<String> exchange = getRestTemplate()
				.exchange(
						"http://localhost:" + getPort() + "/api/user/userteste",
						HttpMethod.GET,
						new HttpEntity<>(headers),
						String.class);
		
		assertEquals(HttpStatus.OK, exchange.getStatusCode());
		String body = exchange.getBody();
		assertNotNull(body);
		
	}
	
}
