package com.danielqueiroz.madqueenserver.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Component
public class VagalumeAPI {

	@Value("${vagalume.token}")
	private String token;
	
	private RestTemplate client;

	public VagalumeAPI() {
		getClient();
	}

	public JSONArray getMusics(String title) throws JSONException {
		
		ResponseEntity<String> response= client.getForEntity("/search.excerpt?q="+ title.replace(" ", "%20")+"&limit=10&apikey" + token, String.class);
		JSONObject jsonBodyObj = new JSONObject(response.getBody());
		JSONArray docs = jsonBodyObj.getJSONObject("response").getJSONArray("docs");
		
		return docs;
	}

	

	public List<String> getMusic(String string) throws JSONException {
		ResponseEntity<String> response= client.getForEntity("/search.php?musid=" + string + "&limit=10&apikey" + token, String.class);
		JSONObject jsonBodyObj = new JSONObject(response.getBody());
		JSONArray jsonArray = jsonBodyObj.getJSONArray("mus");
		
		List<String> letters = new ArrayList<String>();
		
		for (int i = 0; i < jsonArray.length(); i++) {
			String music = jsonArray.getJSONObject(i).getString("text");
			if (music != null) {
				letters.add(music);
			}
		}
		
		return letters;
		
	}
	
	public RestTemplate getClient() {
		if (client == null) {
			this.client = new RestTemplate();
			this.client.setUriTemplateHandler(new DefaultUriBuilderFactory("https://api.vagalume.com.br"));
		}
		return client;
	}

}
