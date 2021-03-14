package com.danielqueiroz.madqueenserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class SecuredController {
	
	@GetMapping
    public ResponseEntity<?> reachSecureEndpoint() {
        return new ResponseEntity<Object>("If your are reading this you reached a secure endpoint", HttpStatus.OK);
    }
}
