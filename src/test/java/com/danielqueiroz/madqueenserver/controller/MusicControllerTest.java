package com.danielqueiroz.madqueenserver.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.danielqueiroz.madqueenserver.helper.TestHelper;
import com.danielqueiroz.madqueenserver.model.Artist;
import com.danielqueiroz.madqueenserver.model.Band;
import com.danielqueiroz.madqueenserver.model.Music;

public class MusicControllerTest extends BaseControllerTest{

	@Test
	public void getMusicList() throws URISyntaxException {
		
		String token = getToken("usuarioteste", "senhateste");
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		
		RequestEntity<Music> requestEntity = new RequestEntity<Music>(headers, HttpMethod.GET, new URI("http://localhost:" + getPort() + "/api/music"));
			
		ResponseEntity<Music[]> exchange = (ResponseEntity<Music[]>) getRestTemplate().exchange(requestEntity, Music[].class);
		exchange.getBody();

		assertEquals(HttpStatus.OK, exchange.getStatusCode());
		
		List<Object> musics = Arrays.asList(exchange.getBody());
		
		assertNotNull(musics);
		assertTrue(musics.size() > 0);
		assertEquals(HttpStatus.OK, exchange.getStatusCode());

	}
	
	
	@Test
	@Transactional
	public void saveMusicAndGetMusicSaved() throws URISyntaxException, MalformedURLException, UnsupportedEncodingException {
		String token = getToken("usuarioteste", "senhateste");
		
		Music music = TestHelper.getMusic();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		
		RequestEntity<Music> requestEntity = new RequestEntity<Music>(music, headers, HttpMethod.POST, new URI("http://localhost:" + getPort() + "/api/music"));
			
		ResponseEntity<String> exchange = getRestTemplate().exchange(requestEntity, String.class);

		assertEquals(HttpStatus.OK, exchange.getStatusCode());
		
		//Get
		String url = new String("http://localhost:" + getPort() + "/api/music?title="+  URLEncoder.encode(music.getTitle(), "UTF-8"));
		RequestEntity<Music> requestGetEntity = new RequestEntity<Music>(headers, HttpMethod.GET,  new URI(url));
			
		ResponseEntity<Music[]> exchangeSearch = (ResponseEntity<Music[]>) getRestTemplate().exchange(requestGetEntity, Music[].class);

		assertEquals(HttpStatus.OK, exchangeSearch.getStatusCode());
		
		List<Music> musics = Arrays.asList(exchangeSearch.getBody());
		
		
		assertNotNull(musics);
		assertTrue(musics.size() > 0);
		assertEquals(HttpStatus.OK, exchange.getStatusCode());

	}
	
	@Test
	@Transactional
	public void saveMusicAndNotGetMusicWithNotExist() throws URISyntaxException, MalformedURLException, UnsupportedEncodingException {
		String token = getToken("usuarioteste", "senhateste");
		
		Music music = TestHelper.getMusic();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		
		RequestEntity<Music> requestEntity = new RequestEntity<Music>(music, headers, HttpMethod.POST, new URI("http://localhost:" + getPort() + "/api/music"));
			
		ResponseEntity<String> exchange = getRestTemplate().exchange(requestEntity, String.class);

		assertEquals(HttpStatus.OK, exchange.getStatusCode());
		
		//Get
		String url = new String("http://localhost:" + getPort() + "/api/music?title="+  URLEncoder.encode("Essa muisica não existe", "UTF-8"));
		RequestEntity<Music> requestGetEntity = new RequestEntity<Music>(headers, HttpMethod.GET,  new URI(url));
			
		ResponseEntity<Music[]> exchangeSearch = (ResponseEntity<Music[]>) getRestTemplate().exchange(requestGetEntity, Music[].class);

		assertEquals(HttpStatus.OK, exchangeSearch.getStatusCode());
		
		List<Music> musics = Arrays.asList(exchangeSearch.getBody());
		
		assertNotNull(musics.isEmpty());

	}
	
	@Test
	@Transactional
	public void saveMusic() throws URISyntaxException {
		String token = getToken("usuarioteste", "senhateste");
		
		Music music = TestHelper.getMusic();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		
		RequestEntity<Music> requestEntity = new RequestEntity<Music>(music, headers, HttpMethod.POST, new URI("http://localhost:" + getPort() + "/api/music"));
			
		ResponseEntity<String> exchange = getRestTemplate().exchange(requestEntity, String.class);

		assertEquals(HttpStatus.OK, exchange.getStatusCode());
	}
	
	@Test
	public void notSaveMusicWithoutArtist() throws URISyntaxException {
		
		String token = getToken("usuarioteste", "senhateste");
		
		Music music = TestHelper.getMusic();
		music.setArtist(null);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		
		RequestEntity<Music> requestEntity = new RequestEntity<Music>(music, headers, HttpMethod.POST, new URI("http://localhost:" + getPort() + "/api/music"));
			
		ResponseEntity<String> exchange = getRestTemplate().exchange(requestEntity, String.class);

		assertEquals(HttpStatus.BAD_REQUEST, exchange.getStatusCode());
		
	}
	
	@Test
	public void notSaveMusicWithoutBand() throws URISyntaxException {
		
		String token = getToken("usuarioteste", "senhateste");
		
		Music music = TestHelper.getMusic();
		music.setBand(null);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		
		RequestEntity<Music> requestEntity = new RequestEntity<Music>(music, headers, HttpMethod.POST, new URI("http://localhost:" + getPort() + "/api/music"));
			
		ResponseEntity<String> exchange = getRestTemplate().exchange(requestEntity, String.class);

		assertEquals(HttpStatus.BAD_REQUEST, exchange.getStatusCode());
		assertEquals("A música precisa ter um uma banda vinculada.", exchange.getBody());
		
	}
	
	
}
