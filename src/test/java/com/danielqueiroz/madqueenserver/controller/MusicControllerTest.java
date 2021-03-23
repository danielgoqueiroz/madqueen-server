package com.danielqueiroz.madqueenserver.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.danielqueiroz.madqueenserver.model.Band;
import com.danielqueiroz.madqueenserver.model.Music;

public class MusicControllerTest extends BaseControllerTest{

	@Test
	public void shoulGetMusicList() throws URISyntaxException {
		
		String token = getToken("usuarioteste", "senhateste");
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		
		RequestEntity<Music> requestEntity = new RequestEntity<Music>(headers, HttpMethod.GET, new URI("http://localhost:" + getPort() + "/api/music"));
			
		ResponseEntity<Music[]> exchange = (ResponseEntity<Music[]>) getRestTemplate().exchange(requestEntity, Music[].class);

		assertEquals(HttpStatus.OK, exchange.getStatusCode());
		
		List<Music> musics = Arrays.asList(exchange.getBody());
		
		
		assertNotNull(musics);
		assertTrue(musics.size() > 0);
		assertEquals(HttpStatus.OK, exchange.getStatusCode());

	}
	
	@Test
	public void shouldNotSaveMusicWithoutArtist() throws URISyntaxException {
		
		String token = getToken("usuarioteste", "senhateste");
		
		Music music = new Music("Música teste", 2020, null, new Band("Banda Test", "Descrição Banda Teste"));

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		
		RequestEntity<Music> requestEntity = new RequestEntity<Music>(music, HttpMethod.POST, new URI("http://localhost:" + getPort() + "/api/music"));
			
		ResponseEntity<String> exchange = getRestTemplate().exchange(requestEntity, String.class);

		assertEquals(HttpStatus.OK, exchange.getStatusCode());
		
		String body = exchange.getBody();
		assertNotNull(body);
		assertEquals(HttpStatus.OK, body);

	}
	
	
}
