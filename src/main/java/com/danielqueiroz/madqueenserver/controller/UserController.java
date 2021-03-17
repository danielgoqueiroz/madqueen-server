package com.danielqueiroz.madqueenserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielqueiroz.madqueenserver.model.User;
import com.danielqueiroz.madqueenserver.model.UserDTO;
import com.danielqueiroz.madqueenserver.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?>getUser(@PathVariable String id) {
		return ResponseEntity.ok(id);
	}
	@PostMapping
	public ResponseEntity<?>signUp(@RequestBody UserDTO user) {
		User save = userService.save(new User(user.getUsername(), user.getPassword(), user.getEmail(), user.getCpf()));
		if (save != null) {
			return ResponseEntity.ok("Novo usuário salvo com sucesso");
		} else {
			return new ResponseEntity(new String("{ mensagem : Erro ao salvar usuário }"), HttpStatus.BAD_REQUEST);
		}
	}

}
