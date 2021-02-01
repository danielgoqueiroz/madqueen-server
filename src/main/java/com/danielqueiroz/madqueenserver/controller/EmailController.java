package com.danielqueiroz.madqueenserver.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.danielqueiroz.madqueenserver.model.Message;
import com.danielqueiroz.madqueenserver.service.EmailService;

@RestController
public class EmailController {

	@PostMapping(value = "/email", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> processEmail(@RequestBody Message message) {
		EmailService mail = new EmailService();
		mail.sendMessage(message);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
}
