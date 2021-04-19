package com.danielqueiroz.madqueenserver.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.danielqueiroz.madqueenserver.helper.TestHelper;
import com.danielqueiroz.madqueenserver.jwt.UsernameAndPasswordAuthenticationRequest;
import com.danielqueiroz.madqueenserver.model.User;
import com.danielqueiroz.madqueenserver.model.UserDTO;
import com.danielqueiroz.madqueenserver.repository.UserRespository;
import com.danielqueiroz.madqueenserver.service.UserService;

public class UserControllerTest extends BaseControllerTest {

	@Test
	public void shouldGetuserByUsernameWithAuthentication() {

		UserDTO newUser = new UserDTO("User Test", "SenhaTeste123", "teste@email.com", "1236547898");
		ResponseEntity<String> postForEntity = getRestTemplate().postForEntity("http://localhost:" + getPort() + "/api/user", newUser, String.class);
		
		assertEquals(HttpStatus.ACCEPTED, postForEntity.getStatusCode());
		
		String body2 = postForEntity.getBody();
		
		String token = getToken(newUser.getUsername(), newUser.getPassword());
		
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
