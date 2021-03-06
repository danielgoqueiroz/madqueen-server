package com.danielqueiroz.madqueenserver.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.danielqueiroz.madqueenserver.constants.RoleCons;
import com.danielqueiroz.madqueenserver.model.Role;
import com.danielqueiroz.madqueenserver.model.User;
import com.danielqueiroz.madqueenserver.repository.RoleRepository;
import com.danielqueiroz.madqueenserver.service.UserService;


@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		List<Role> roles = roleRepository.findAll();
		if (roles.isEmpty()) {
			Role admin = new Role(RoleCons.ADMIN);
			Role user = new Role(RoleCons.USER);
			roleRepository.save(user);
			roleRepository.save(admin);
		}
		
		List<User> users = userService.findAll();
		if (users.isEmpty()) {
			createUser("teste", "teste@email.com", passwordEncoder.encode("123456789"), "65498732165", new Role("USER"));
			createUser("testeAdmin", "teste-admin@email.com", passwordEncoder.encode("123456789"),"65498732168" ,new Role("ADMIN"));
		}
	}

	private void createUser(String name, String email, String password, String cpf, Role role) {
		
		User user = new User(name, password, email, cpf, new ArrayList<Role>(Arrays.asList(role)));
		userService.save(user);
	}

}
