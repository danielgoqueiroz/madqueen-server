package com.danielqueiroz.madqueenserver.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.danielqueiroz.madqueenserver.model.Artist;
import com.danielqueiroz.madqueenserver.model.Music;

public class ArtistControllerTest extends BaseControllerTest {

	@Test
	@Transactional
	public void shouldgetArtist() throws URISyntaxException {
		String token = getToken("usuarioteste", "senhateste");
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		
		RequestEntity<Music> requestEntity = new RequestEntity<Music>(headers, HttpMethod.GET, new URI("http://localhost:" + getPort() + "/api/artist"));
			
		ResponseEntity<Artist[]> exchange = getRestTemplate().exchange(requestEntity, Artist[].class);

		assertEquals(HttpStatus.OK, exchange.getStatusCode());
		
		List<Artist> artists = Arrays.asList(exchange.getBody());
		
		
		assertNotNull(artists);
		assertTrue(artists.size() > 0);
		assertEquals(HttpStatus.OK, exchange.getStatusCode());
	}
	
}
