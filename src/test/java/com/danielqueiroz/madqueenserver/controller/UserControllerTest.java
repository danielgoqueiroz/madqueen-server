package com.danielqueiroz.madqueenserver.controller;

import static com.danielqueiroz.madqueenserver.constants.SecurityConstants.HEADER_AUTHORIZATION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import com.danielqueiroz.madqueenserver.jwt.UsernameAndPasswordAuthenticationRequest;
import com.danielqueiroz.madqueenserver.model.UserDTO;

public class UserControllerTest extends BaseControllerTest {

	@Test
	public void shouldGetuserByUsernameWithAuthentication() throws Exception {

		UserDTO user = new UserDTO("Usuário Teste", "SenhaTeste123", "teste@email.com", "1236547898");

		createUser(user);

		UsernameAndPasswordAuthenticationRequest userLogin = new UsernameAndPasswordAuthenticationRequest(user.getUsername(),
				user.getPassword());

		String token = getLoginToken(userLogin);

		MockHttpServletResponse response = mockMvc
				.perform(get("/user/" + user.getUsername()).header(HEADER_AUTHORIZATION, token))
				.andExpect(status().is(200)).andReturn().getResponse();

		String jsonResponse = response.getContentAsString(Charset.defaultCharset());

		assertNotNull(jsonResponse);
		JSONObject jsonObj = new JSONObject(jsonResponse);
		assertEquals(user.getUsername(), jsonObj.getString("username"));
		assertEquals(user.getEmail(), jsonObj.getString("email"));

	}

	

}
