package com.danielqueiroz.madqueenserver.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.danielqueiroz.madqueenserver.model.User;
import com.danielqueiroz.madqueenserver.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@CrossOrigin("*")
	@PostMapping(value = "/token", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> login(@RequestBody final String username, final String password) {
		User findByNameAndPassword = service.findByNameAndPassword(username, password);
		
		
		
		return ResponseEntity.ok(HttpStatus.OK);
	}

}
