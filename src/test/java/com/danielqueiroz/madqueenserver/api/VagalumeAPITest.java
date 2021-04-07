package com.danielqueiroz.madqueenserver.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VagalumeAPITest {

	@Autowired
	private VagalumeAPI api; 
	
	@Test
	public void shouldGetMusis() throws JSONException {
		JSONArray musics = api.getMusics("vamos fugir");
		assertNotNull(musics);
		assertTrue(musics.length() > 0);
	}
	
	@Test
	public void shoulGetMudicByID() throws JSONException {
		List<String> musics = api.getMusic("l3ade68b7g3be34ea3");
		assertNotNull(musics);
		assertTrue(musics.size() > 0);
	}
	
}
