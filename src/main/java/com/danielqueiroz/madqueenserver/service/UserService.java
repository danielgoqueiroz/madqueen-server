package com.danielqueiroz.madqueenserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.danielqueiroz.madqueenserver.constants.UserCons;
import com.danielqueiroz.madqueenserver.model.Role;
import com.danielqueiroz.madqueenserver.model.User;
import com.danielqueiroz.madqueenserver.repository.UserRespository;
import com.danielqueiroz.madqueenserver.utils.Validation;

@Service
public class UserService {
	
	@Autowired
	private UserRespository userRespository;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	public User findById(Long id) {
		return userRespository.findById(id).get();
	}

	public User findByUsername(String username) {
		return userRespository.findByUsername(username);
	}

	public User findByUsernameAndPassword(String username, String password) {
		User user = userRespository.findByUsernameAndPassword(username, password);
		return user;
	}

	public List<User> findAll() {
		return userRespository.findAll();
	}

	public User save(User user) {
		
		if (Validation.validate(user.getUsername(), UserCons.NAME_SIZE_MIN, UserCons.NAME_SIZE_MAX)
				&& Validation.validate(user.getEmail(), UserCons.EMAIL_SIZE_MIN, UserCons.EMAIL_SIZE_MAX)
				&& !isUserExist(user)) {
			if (user.getRoles().isEmpty()) {
				user.addDefaultRole();
			}
			List<Role> rolesUpdated = new ArrayList<Role>();
			user.setRoles(rolesUpdated);
			user.setPassword(user.getPassword());
			return userRespository.save(user);
		}
		return null;
	}
	
	public boolean isUserAndPasswordValid(User user) {
		User userFinded = userRespository.findByUsername(user.getUsername());
		if (userFinded != null) {
//			return passwordEncoder.matches(passwordEncoder.encode(user.getPassword()), userFinded.getPassword());
			return true;
		}
		return false;

	}

	private boolean isUserExist(User user) {
		return userRespository.findByUsernameAndPassword(user.getUsername(), user.getPassword()) != null;
	}

//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return passwordEncoder;
//	}


}
