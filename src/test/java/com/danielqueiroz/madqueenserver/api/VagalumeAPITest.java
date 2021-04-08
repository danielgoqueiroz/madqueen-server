package com.danielqueiroz.madqueenserver.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.test.context.SpringBootTest;

import com.danielqueiroz.madqueenserver.api.model.MusicDTO;

@SpringBootTest
public class VagalumeAPITest {

	@Autowired
	private VagalumeAPI api; 
	
	@Test
	public void shouldGetMusis() throws JSONException {
		List<MusicDTO> musics = api.getMusics("vamos fugir");
		
		assertNotNull(musics);
		assertTrue(musics.size() > 0);
	}
	
	@Test
	public void shoulGetMudicByID() throws JSONException {
		
		MusicDTO musicDTO = api.getMusic("3ade68b5gd4a8eda3");
		assertNotNull(musicDTO);
		assertNotNull(musicDTO.getId());
		assertNotNull(musicDTO.getTitle());
		assertNotNull(musicDTO.getBand());
		
		assertTrue(musicDTO.getLetters().size() == 2);
		
	}
	
	@Test
	public void shouldFindMusicAndGetLetter() throws JSONException {
		
		List<MusicDTO> musics = api.getMusics("heaven and hell");
		assertNotNull(musics);
		assertTrue(musics.size() > 0);
		
		MusicDTO musicDTO2 = musics.get(0);
		
		MusicDTO musicDTO = api.getMusic(musicDTO2.getId());
		assertNotNull(musicDTO);
		assertNotNull(musicDTO.getId());
		assertNotNull(musicDTO.getTitle());
		assertNotNull(musicDTO.getBand());
		
		assertTrue(musicDTO.getLetters().size() == 2);
		
	}
	
}
