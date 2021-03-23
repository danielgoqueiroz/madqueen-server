package com.danielqueiroz.madqueenserver.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class StatusControllerTest extends BaseControllerTest{

	@Test
	public void shouldRequestStatusWithOk() {
		ResponseEntity<String> exchange = getRestTemplate().exchange("http://localhost:" + getPort() + "/api/status", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, exchange.getStatusCode());
		assertEquals("On", exchange.getBody());
	}
}
