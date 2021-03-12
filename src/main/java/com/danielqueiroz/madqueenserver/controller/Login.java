//package com.danielqueiroz.madqueenserver.controller;
//
//import javax.ws.rs.core.MediaType;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.danielqueiroz.madqueenserver.model.User;
//import com.danielqueiroz.madqueenserver.service.UserService;
//
//@RestController
//@RequestMapping("/login")
//public class Login {
//
//	@Autowired
//	UserService service;
//	
//	@CrossOrigin("*")
//	@PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
//	public ResponseEntity<?> login(@RequestBody final User user) {
//		
//		User findByNameAndPassword = service.findByUsername(user.getUsername());;
//		
//		boolean isValid = service.isUserAndPasswordValid(user);
//
//		return ResponseEntity.ok(user.getUsername());
//		
//	}
//	
//}
