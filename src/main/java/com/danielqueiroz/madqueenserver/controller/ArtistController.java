package com.danielqueiroz.madqueenserver.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielqueiroz.madqueenserver.model.Artist;
import com.danielqueiroz.madqueenserver.service.ArtistService;

@RestController
@RequestMapping("/artist")
public class ArtistController {

	@Autowired
	private ArtistService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Object[]> getArtists() {
		
		List<Artist> artists = service.getAll();
		
		return ResponseEntity.ok(artists.toArray());
	}
	
	
}
