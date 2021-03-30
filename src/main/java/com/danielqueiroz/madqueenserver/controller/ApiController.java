package com.danielqueiroz.madqueenserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApiController {
	
	@GetMapping
	public ResponseEntity<String> getApi() {
		return ResponseEntity.ok().build();
	}

}
