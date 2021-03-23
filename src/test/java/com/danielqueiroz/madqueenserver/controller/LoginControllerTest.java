package com.danielqueiroz.madqueenserver.controller;

import org.junit.jupiter.api.Test;

public class LoginControllerTest extends BaseControllerTest{
	
	@Test
	public void shouldNotGetUserByWithoutAuthentication() {
		
		getToken("usuerio inexistente", "123");
		
	}

}
