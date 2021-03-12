//package com.danielqueiroz.madqueenserver.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.danielqueiroz.madqueenserver.model.User;
//import com.danielqueiroz.madqueenserver.model.UserDTO;
//import com.danielqueiroz.madqueenserver.service.UserService;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//	
//	@Autowired
//	UserService userService;
//	
//	@PostMapping("/save")
//	public ResponseEntity<?>signUp(@RequestBody UserDTO user) {
//		User save = userService.save(new User(user.getUsername(), user.getPassword()));
//		if (save != null) {
//			return new ResponseEntity<String>(HttpStatus.OK);
//		} else {
//			return new ResponseEntity(new String("{ mensagem : Erro ao salvar usuário }"), HttpStatus.BAD_REQUEST);
//		}
//	}
//
//}
