package com.danielqueiroz.madqueenserver.controller;

import java.util.List;

import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	public ResponseEntity<Object[]> getAll() {
		
		List<Artist> artists = service.getAll();
		
		return ResponseEntity.ok(artists.toArray());
	}
	
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@CrossOrigin
	@PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> save(@RequestBody Artist artist) {
		System.out.println("teste");
		try {
			service.save(artist);
			
		} catch (ValidationException e) {
			return ResponseEntity.badRequest().body(String.format("Artista não pode ser salvo por ocorreu o seguinte erro: %s",e));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Artista salvo com sucesso");
		
		
	}
	
	
}
