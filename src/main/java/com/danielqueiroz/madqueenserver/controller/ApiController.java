package com.danielqueiroz.madqueenserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class ApiController {
	
	@GetMapping
	public ResponseEntity<String> getApi() {
		return ResponseEntity.ok().build();
	}

}
