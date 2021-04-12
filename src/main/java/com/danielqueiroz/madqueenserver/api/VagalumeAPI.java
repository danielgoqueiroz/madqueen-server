package com.danielqueiroz.madqueenserver.api;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.danielqueiroz.madqueenserver.api.model.LetterDTO;
import com.danielqueiroz.madqueenserver.api.model.MusicDTO;

@Component
public class VagalumeAPI {

	@Value("${vagalume.token}")
	private String token;
	
	private RestTemplate client;

	public List<MusicDTO> getMusics(String titleFolter) throws JSONException {
		RestTemplate restClient = getClient();
		ResponseEntity<String> response= restClient.getForEntity("/search.excerpt?q="+ titleFolter.replace(" ", "%20")+"&limit=10&apikey" + token, String.class);
		
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

	

	public MusicDTO getMusic(String id) throws JSONException {
		RestTemplate restClient = getClient();
		ResponseEntity<String> response= restClient.getForEntity("/search.php?musid=" + id + "&limit=10&apikey=" + token, String.class);
		JSONObject jsonBodyObj = new JSONObject(response.getBody());
		JSONArray musicsJsonArray = jsonBodyObj.getJSONArray("mus");
		
		JSONObject artObj = jsonBodyObj.getJSONObject("art");
		String idValue = artObj.getString("id");
		String band = artObj.getString("name");
		
		MusicDTO musicDTO = new MusicDTO();
		musicDTO.setId(idValue);
		musicDTO.setBand(band);
		
		for (int i = 0; i < musicsJsonArray.length(); i++) {
			
			JSONObject musicObj = musicsJsonArray.getJSONObject(i);

			String name = musicObj.getString("name");
			String music = musicObj.getString("text");
			Integer lang = musicObj.getInt("lang");
			
			musicDTO.setTitle(name);
			
			if (music != null) {
				musicDTO.getLetters().add(new LetterDTO(music, lang));
			}
			
			JSONArray translationsJson= musicObj.getJSONArray("translate");
			if (translationsJson!= null) {
				for (int j = 0; j < translationsJson.length(); j++) {
					JSONObject translation = translationsJson.getJSONObject(j);
					
					int translLang = translation.getInt("lang");
					String translationText = translation.getString("text");
					
					musicDTO.getLetters().add(new LetterDTO(translationText, translLang));
				}
			}
		}
		
		return musicDTO;
		
	}
	
	@Bean
	public RestTemplate getClient() {
		if (client == null) {
			this.client = new RestTemplate();
			this.client.setUriTemplateHandler(new DefaultUriBuilderFactory("https://api.vagalume.com.br"));
		}
		return client;
	}

}
