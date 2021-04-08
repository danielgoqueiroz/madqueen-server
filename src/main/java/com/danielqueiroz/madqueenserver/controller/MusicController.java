package com.danielqueiroz.madqueenserver.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.danielqueiroz.madqueenserver.Exceptions.ValidationException;
import com.danielqueiroz.madqueenserver.api.model.MusicDTO;
import com.danielqueiroz.madqueenserver.model.Music;
import com.danielqueiroz.madqueenserver.service.MusicService;
import com.google.common.base.Strings;

@RestController
@RequestMapping("/music")
public class MusicController {
	
	@Autowired
	private MusicService service;
	
	@CrossOrigin
	@GetMapping(path = "/search",produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> searchMusic(@RequestParam(required = false, name="title") String title) {
		List<MusicDTO> musics = new ArrayList<MusicDTO>();
		try {
			musics = service.searchMusics(title);
		} catch (JSONException e) {
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			e.printStackTrace();
		}
		return ResponseEntity.ok(musics);
	}
	
	@CrossOrigin
	@GetMapping(produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getMusics(@RequestParam(required = false, name="title") String title) {
		List<Music> musics = service.getMusics(title);
		return ResponseEntity.ok(musics.toArray());
	}
	
	@CrossOrigin
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
