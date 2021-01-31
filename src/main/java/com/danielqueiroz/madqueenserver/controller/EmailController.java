package com.danielqueiroz.madqueenserver.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielqueiroz.madqueenserver.service.EmailService;

@RestController
public class EmailController {

	@GetMapping(value = "/email", produces = MediaType.APPLICATION_JSON)
	public String processEmail() {
		return "Enviado";
	}
	
}
