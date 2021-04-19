package com.danielqueiroz.madqueenserver.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.danielqueiroz.madqueenserver.helper.TestHelper;
import com.danielqueiroz.madqueenserver.jwt.UsernameAndPasswordAuthenticationRequest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BaseControllerTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public TestRestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(TestRestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public String getToken(String username, String password) {
		UsernameAndPasswordAuthenticationRequest userLogin = new UsernameAndPasswordAuthenticationRequest(username, password);
		
		ResponseEntity<JSONObject> postForEntity = getRestTemplate().postForEntity("http://localhost:" + getPort() + "/api/login", userLogin, JSONObject.class);
		assertEquals(HttpStatus.OK, postForEntity.getStatusCode(), "Não foi possível obter o token de acesso.");
		String token = postForEntity.getHeaders().get("Authorization").get(0);
		return token;
	}
}
