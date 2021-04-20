package com.danielqueiroz.madqueenserver.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

public class StatusControllerTest extends BaseControllerTest{

	@Test
	public void shouldRequestStatusWithOk() throws Exception {
		
		mockMvc.perform(get("/status", new Object[] {}))
			.andExpect(status().isOk())
			.andExpect(content().string("On"));
		
	}
}
