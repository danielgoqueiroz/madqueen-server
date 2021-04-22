package com.danielqueiroz.madqueenserver.controller;

import static com.danielqueiroz.madqueenserver.constants.SecurityConstants.HEADER_AUTHORIZATION;
import static com.danielqueiroz.madqueenserver.helper.TestHelper.getArtist;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.danielqueiroz.madqueenserver.model.Artist;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ArtistControllerTest extends BaseControllerTest {
	
	@Test
	public void shouldSaveArtist() throws JsonProcessingException, Exception {
		
		String token = createDefaultUser();
		
		Artist artist = getArtist();
		
		mockMvc.perform(
				post("/artist")
					.header(HEADER_AUTHORIZATION, token)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(artist)))
			.andExpect(status().isCreated());
		
//		MockHttpServletResponse response = mockMvc.perform(get("/artist").headers(headers))
//				.andExpect(status().is(200))
//				.andReturn().getResponse();
				
//		String jsonResponse = response.getContentAsString();
//		
//		Music[] musics = mapper.readValue(jsonResponse, Music[].class);
//		
//		System.out.println(musics);
			
//		ResponseEntity<Artist[]> exchange = getRestTemplate().exchange(jsonResponse, Artist[].class);
//
//		assertEquals(HttpStatus.OK, exchange.getStatusCode());
//		
//		List<Artist> artists = Arrays.asList(exchange.getBody());
//		
//		
//		assertNotNull(artists);
//		assertTrue(artists.size() > 0);
//		assertEquals(HttpStatus.OK, exchange.getStatusCode());
	}
	
//	@Test
//	public void shouldGetArtist() throws JsonProcessingException, Exception {
//		String token = getLoginToken("usuarioteste", "senhateste");
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Authorization", token);
//		
//		RequestEntity<Music> requestEntity = new RequestEntity<Music>(headers, HttpMethod.GET, new URI("http://localhost:" + getPort() + "/api/artist"));
//			
//		ResponseEntity<Artist[]> exchange = getRestTemplate().exchange(requestEntity, Artist[].class);
//
//		assertEquals(HttpStatus.OK, exchange.getStatusCode());
//		
//		List<Artist> artists = Arrays.asList(exchange.getBody());
//		
//		
//		assertNotNull(artists);
//		assertTrue(artists.size() > 0);
//		assertEquals(HttpStatus.OK, exchange.getStatusCode());
//	}
	
}
