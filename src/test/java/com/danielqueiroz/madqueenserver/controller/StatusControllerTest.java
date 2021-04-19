package com.danielqueiroz.madqueenserver.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
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
