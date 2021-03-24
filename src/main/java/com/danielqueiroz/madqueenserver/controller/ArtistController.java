package com.danielqueiroz.madqueenserver.controller;

import java.util.List;

import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<?> save(Artist artist) {
		
		try {
			service.save(artist);
			
		} catch (ValidationException e) {
			return ResponseEntity.badRequest().body(String.format("Artista não pode ser salvo por ocorreu o seguinte erro: %s",e));
		}
		return ResponseEntity.ok("Artista salvo com sucesso");
		
		
	}
	
	
}
