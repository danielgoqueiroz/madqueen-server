package com.danielqueiroz.madqueenserver.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Status {

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> processEmail() {
		return ResponseEntity.ok("Running");
	}
	
}
