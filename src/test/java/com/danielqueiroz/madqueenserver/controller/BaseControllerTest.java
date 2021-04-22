package com.danielqueiroz.madqueenserver.controller;

import static com.danielqueiroz.madqueenserver.constants.SecurityConstants.HEADER_AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import com.danielqueiroz.madqueenserver.helper.TestHelper;
import com.danielqueiroz.madqueenserver.jwt.UsernameAndPasswordAuthenticationRequest;
import com.danielqueiroz.madqueenserver.model.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BaseControllerTest {
	
	static TestHelper helper;
	static ObjectMapper mapper;
	
	@BeforeAll
	public static void init() {
		helper = new TestHelper();
		mapper = new ObjectMapper();
	}
	
	@Autowired
	MockMvc mockMvc;
	
	@LocalServerPort
	private int port;
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public String getLoginToken(UsernameAndPasswordAuthenticationRequest userLogin)
			throws Exception, JsonProcessingException {
		String token = mockMvc.perform(post("/login").content(helper.getObjectMapper().writeValueAsString(userLogin)))
				.andExpect(status().isOk()).andExpect(header().exists(HEADER_AUTHORIZATION))
				.andReturn()
				.getResponse()
				.getHeader(HEADER_AUTHORIZATION);
		return token;
	}

	public void createUser(UserDTO user) throws Exception {
		mockMvc.perform(
				post("/user").content(mapper.writeValueAsString(user))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	public String createDefaultUser() throws Exception {
		UserDTO user = helper.getUserDTO();
		createUser(user);
		return getLoginToken(new UsernameAndPasswordAuthenticationRequest(user.getUsername(), user.getPassword()));
	}
	
	public String createUserAdmin() throws Exception {
		UserDTO user = helper.getUserDTO();
		createUser(user);
		return getLoginToken(new UsernameAndPasswordAuthenticationRequest(user.getUsername(), user.getPassword()));
	}

}
