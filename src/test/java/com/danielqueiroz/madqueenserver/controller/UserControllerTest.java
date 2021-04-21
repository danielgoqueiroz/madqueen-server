package com.danielqueiroz.madqueenserver.controller;

import static com.danielqueiroz.madqueenserver.constants.SecurityConstants.HEADER_AUTHORIZATION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import com.danielqueiroz.madqueenserver.model.LoginDTO;
import com.danielqueiroz.madqueenserver.model.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserControllerTest extends BaseControllerTest {

	@Test
	public void shouldGetuserByUsernameWithAuthentication() throws Exception {
		
		UserDTO newUser = new UserDTO("User Test", "SenhaTeste123", "teste@email.com", "1236547898");
		ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(newUser);
		
		mockMvc.perform(
				post("/user").content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		LoginDTO login = new LoginDTO(newUser.getUsername(), newUser.getPassword());
		
		String token = mockMvc.perform(post("/login").content(mapper.writeValueAsString(login)))
				.andExpect(status().isOk())
				.andExpect(header().exists(HEADER_AUTHORIZATION))
				.andReturn().getResponse().getHeader(HEADER_AUTHORIZATION);

		MockHttpServletResponse response = mockMvc.perform(
				get("/user/" + newUser.getUsername()).header(HEADER_AUTHORIZATION, token))
				.andReturn()
				.getResponse();
		
		assertEquals(200, response.getStatus());
		String jsonResponse = response.getContentAsString();
		
		assertNotNull(jsonResponse);
		JSONObject jsonObj = new JSONObject(jsonResponse);
		assertEquals(newUser.getUsername(), jsonObj.getString("username"));
		assertEquals(newUser.getEmail(), jsonObj.getString("email"));

	}
	
}
