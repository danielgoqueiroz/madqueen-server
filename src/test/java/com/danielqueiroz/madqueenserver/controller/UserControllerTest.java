package com.danielqueiroz.madqueenserver.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.danielqueiroz.madqueenserver.model.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserControllerTest extends BaseControllerTest {

	@Test
	public void shouldGetuserByUsernameWithAuthentication() throws Exception {
		
		UserDTO newUser = new UserDTO("User Test", "SenhaTeste123", "teste@email.com", "1236547898");
		ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(newUser);
		
		MvcResult andReturn = mockMvc.perform(
				post("/user").content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(header().exists("Authorization"))
				.andReturn();
		
		System.out.println(andReturn);
		

//		ResponseEntity<String> postForEntity = getRestTemplate().postForEntity("http://localhost:" + getPort() + "/api/user", newUser, String.class);
//		
//		assertEquals(HttpStatus.ACCEPTED, postForEntity.getStatusCode());
//		
//		String body2 = postForEntity.getBody();
//		
//		String token = getToken(newUser.getUsername(), newUser.getPassword());
//		
//		assertNotNull(token);
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Authorization", token);
//
//		ResponseEntity<String> exchange = getRestTemplate()
//				.exchange(
//						"http://localhost:" + getPort() + "/api/user/userteste",
//						HttpMethod.GET,
//						new HttpEntity<>(headers),
//						String.class);
//		
//		assertEquals(HttpStatus.OK, exchange.getStatusCode());
//		String body = exchange.getBody();
//		assertNotNull(body);
//		
	}
	
}
