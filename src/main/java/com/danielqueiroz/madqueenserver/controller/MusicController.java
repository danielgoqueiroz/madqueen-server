package com.danielqueiroz.madqueenserver.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielqueiroz.madqueenserver.Exceptions.ValidationException;
import com.danielqueiroz.madqueenserver.model.Music;
import com.danielqueiroz.madqueenserver.service.MusicService;

@RestController
@RequestMapping("/music")
public class MusicController {
	
	@Autowired
	private MusicService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Object[]> getMusics() {
		List<Music> musics = service.getMusics();
		return ResponseEntity.ok(musics.toArray());
	}
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> saveMusic(@RequestBody Music music) {
		try {
			service.save(music);
			return ResponseEntity.ok().build();
		} catch (ValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
}
