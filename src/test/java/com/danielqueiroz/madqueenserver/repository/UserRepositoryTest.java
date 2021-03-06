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
	UserRespository repository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Test
	@Transactional
	public void shouldSaveUser() {
		
		Role role = new Role().user();
		Role findByDescription = roleRepository.findByDescription(role.getDescription());
		User user = new User("manu", "manu123", "manu@email.com", "0654321654", findByDescription);
		
		User saved = repository.save(user);
		
		assertNotNull(saved.getId());
		assertNotNull(saved.getName());
		assertNotNull(saved.getCpf());
		assertNotNull(saved.getPassword());
		assertNotNull(saved.getName());
		assertTrue(!saved.getRoles().isEmpty());
		assertNotNull(saved.getRoles().get(0).getId());
	}
}
