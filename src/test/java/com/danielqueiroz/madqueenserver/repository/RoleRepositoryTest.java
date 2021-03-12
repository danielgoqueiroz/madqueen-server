package com.danielqueiroz.madqueenserver.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.danielqueiroz.madqueenserver.constants.RoleCons;
import com.danielqueiroz.madqueenserver.model.Role;

@SpringBootTest
public class RoleRepositoryTest {
	
	@Autowired
	RoleRepository repository;
	@Test
	@Transactional
	public void findRole() {
		Role findByDescription = repository.findByDescription(RoleCons.USER);
		
		assertNotNull(findByDescription.getId());
	}
	
}
