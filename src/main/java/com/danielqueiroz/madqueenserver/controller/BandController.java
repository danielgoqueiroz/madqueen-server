package com.danielqueiroz.madqueenserver.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danielqueiroz.madqueenserver.Exceptions.ValidationException;
import com.danielqueiroz.madqueenserver.model.Band;
import com.danielqueiroz.madqueenserver.service.BandService;

@RestController
@RequestMapping("/band")
public class BandController {

	@Autowired
	private BandService service;
	
	@CrossOrigin	
	@GetMapping(produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getMusics(@RequestParam(required = false, name="title") String title) {
		List<Band> bands = service.getBands();
		return ResponseEntity.ok(bands.toArray());
	}
	
	@CrossOrigin	
	@PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> save(@RequestBody Band band) {
		Band bandSaved;
		try {
			bandSaved = service.save(band);
		} catch (ValidationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.ok("Banda salva com sucesso.");
	}
	
}
