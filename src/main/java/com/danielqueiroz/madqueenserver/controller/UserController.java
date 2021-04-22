package com.danielqueiroz.madqueenserver.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielqueiroz.madqueenserver.Exceptions.ValidationException;
import com.danielqueiroz.madqueenserver.model.User;
import com.danielqueiroz.madqueenserver.model.UserDTO;
import com.danielqueiroz.madqueenserver.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@CrossOrigin
	@PostMapping(consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<?>createUser(@RequestBody UserDTO userDTO) {

		User user = new User(userDTO);
		User userCreated;
		try {
			userCreated = userService.save(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
		} catch (ValidationException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	
	@CrossOrigin
	@GetMapping("/{username}")
	public ResponseEntity<?>getUser(@PathVariable String username) {
		User findByUsername;
		try {
			findByUsername = userService.findByUsername(username);
		} catch (ValidationException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		UserDTO user = new UserDTO();
		user.setUsername(findByUsername.getUsername());
		user.setEmail(findByUsername.getEmail());
		return ResponseEntity.ok(user);
	}
	
}
