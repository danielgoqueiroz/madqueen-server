package com.danielqueiroz.madqueenserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielqueiroz.madqueenserver.model.User;
import com.danielqueiroz.madqueenserver.model.UserDTO;
import com.danielqueiroz.madqueenserver.service.UserService;
import com.google.common.net.MediaType;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(path = "/teste", produces = javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public ResponseEntity<String> test() {
		String teste = "teste";
		
		return ResponseEntity.ok(teste);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<?>getUser(@PathVariable String username) {
		User findByUsername = userService.findByUsername(username);
		UserDTO user = new UserDTO();
		user.setUsername(findByUsername.getUsername());
		user.setEmail(findByUsername.getEmail());
		return ResponseEntity.ok(user);
	}
	
}
