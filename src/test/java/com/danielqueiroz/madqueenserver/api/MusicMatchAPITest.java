package com.danielqueiroz.madqueenserver.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.MalformedURLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.danielqueiroz.madqueenserver.api.model.TrackDTO;

@SpringBootTest
public class MusicMatchAPITest {

	@Autowired
	private MusixMatchAPI api;
	
	@Test
	public void shouldGteResult() throws JSONException, MalformedURLException {
		String token = api.getToken();
		assertNotNull(token);
		ResponseEntity<String> result = api.getResult();
		
		JSONObject json = new JSONObject(result.getBody());
		assertNotNull(json);
		JSONObject messageJObj = json.getJSONObject("message");
		assertNotNull(messageJObj);
		JSONObject bodyJObj = messageJObj.getJSONObject("body");
		assertNotNull(bodyJObj);
		JSONArray trackListJArray = bodyJObj.getJSONArray("track_list");
		assertNotNull(trackListJArray);
		
		TrackDTO track = new TrackDTO();
		
		System.out.println(trackListJArray);
	}
	
	
}
