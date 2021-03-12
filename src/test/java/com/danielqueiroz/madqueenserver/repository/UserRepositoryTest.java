package com.danielqueiroz.madqueenserver.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.danielqueiroz.madqueenserver.model.Role;
import com.danielqueiroz.madqueenserver.model.User;

@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	UserRespository userRepository;
	
	@Test
	@Transactional
	public void shouldSaveUser() {
		
		User user = new User("manu", "manu123", "manu@email.com", "0654321654", new Role().user());
		
		User saved = userRepository.save(user);
		
		assertNotNull(saved.getId());
		assertNotNull(saved.getUsername());
		assertNotNull(saved.getCpf());
		assertNotNull(saved.getPassword());
		assertNotNull(saved.getUsername());
		assertTrue(!saved.getRoles().isEmpty());
		assertNotNull(saved.getRoles().get(0).getId());
	}
}
