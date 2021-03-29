package com.danielqueiroz.madqueenserver.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
@CrossOrigin(origins = "*")
public class StatusController {

	@GetMapping(produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> initiateStatus() {
		return ResponseEntity.ok("On");
	}
	
}
