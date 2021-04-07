package com.danielqueiroz.madqueenserver.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.danielqueiroz.madqueenserver.api.model.MusicDTO;

@Component
public class VagalumeAPI {

	@Value("${vagalume.token}")
	private String token;
	
	private RestTemplate client;

	public VagalumeAPI() {
		getClient();
	}

	public List<MusicDTO> getMusics(String titleFolter) throws JSONException {
		
		ResponseEntity<String> response= client.getForEntity("/search.excerpt?q="+ titleFolter.replace(" ", "%20")+"&limit=10&apikey" + token, String.class);
		JSONObject jsonBodyObj = new JSONObject(response.getBody());
		JSONArray docs = jsonBodyObj.getJSONObject("response").getJSONArray("docs");
		
		List<MusicDTO> musicsDTO = new ArrayList<MusicDTO>();
		
		for (int i = 0; i < docs.length(); i++) {
			JSONObject musicObj = docs.getJSONObject(i);
			String id = musicObj.getString("id");
			String band = musicObj.getString("band");
			String title = musicObj.getString("title");
			
			MusicDTO musicDTO = new MusicDTO(id, title, band);
			musicsDTO.add(musicDTO);
		}
		
		
		return musicsDTO;
	}

	

	public MusicDTO getMusic(String string) throws JSONException {
		ResponseEntity<String> response= client.getForEntity("/search.php?musid=" + string + "&limit=10&apikey" + token, String.class);
		JSONObject jsonBodyObj = new JSONObject(response.getBody());
		JSONArray musicsJsonArray = jsonBodyObj.getJSONArray("mus");
		
		String id = jsonBodyObj.getJSONObject("art").getString("id");
		String band = jsonBodyObj.getJSONObject("art").getString("name");
		
		MusicDTO musicDTO = new MusicDTO();
		musicDTO.setId(id);
		musicDTO.setBand(band);
		
		for (int i = 0; i < musicsJsonArray.length(); i++) {
			String name = musicsJsonArray.getJSONObject(i).getString("name");
			musicDTO.setTitle(name);
			String music = musicsJsonArray.getJSONObject(i).getString("text");
			if (music != null) {
				musicDTO.getLetters().add(music);
			}
		}
		
		return musicDTO;
		
	}
	
	public RestTemplate getClient() {
		if (client == null) {
			this.client = new RestTemplate();
			this.client.setUriTemplateHandler(new DefaultUriBuilderFactory("https://api.vagalume.com.br"));
		}
		return client;
	}

}
