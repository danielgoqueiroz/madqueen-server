package com.danielqueiroz.madqueenserver.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.danielqueiroz.madqueenserver.Exceptions.ValidationException;
import com.danielqueiroz.madqueenserver.constants.UserCons;
import com.danielqueiroz.madqueenserver.model.Role;
import com.danielqueiroz.madqueenserver.model.User;
import com.danielqueiroz.madqueenserver.repository.RoleRepository;
import com.danielqueiroz.madqueenserver.repository.UserRespository;
import com.danielqueiroz.madqueenserver.utils.Validation;

@Service
public class UserService {
	
	@Autowired
	private UserRespository userRespository;
	
	@Autowired
	private RoleRepository roleRespository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User findById(Long id) {
		return userRespository.findById(id).get();
	}

	public User findByUsername(String username) throws ValidationException {
		User findByUsername = userRespository.findByUsername(username);
		return findByUsername;
	}

	public User findByUsernameAndPassword(String username, String password) {
		User user = userRespository.findByUsernameAndPassword(username, password);
		return user;
	}

	public List<User> findAll() {
		return userRespository.findAll();
	}

	public User save(User user) throws ValidationException {
		
		User findByUsername = userRespository.findByUsername(user.getUsername());
		
		if (findByUsername != null) {
			throw new ValidationException("Usuário já cadastrado");
		}
		
		if (Validation.validate(user.getUsername(), UserCons.NAME_SIZE_MIN, UserCons.NAME_SIZE_MAX)
				&& Validation.validate(user.getEmail(), UserCons.EMAIL_SIZE_MIN, UserCons.EMAIL_SIZE_MAX)
				&& !isUserExist(user)) {
			if (user.getRoles().isEmpty()) {
				user.addDefaultRole();
			}
			
			
			List<Role> roles = user.getRoles();
			List<Role> dbRoles = roles.stream().map(role -> roleRespository.findByDescription(role.getDescription())).collect(Collectors.toList());
			
			if (dbRoles == null) {
				roleRespository.save(new Role().user());
			}
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRoles(dbRoles);
			return userRespository.save(user);
		}
		return null;
	}
	
	public boolean isUserAndPasswordValid(User user) {
		User userFinded = userRespository.findByUsername(user.getUsername());
		if (userFinded != null ) {
			return true;
		}
		return false;

	}

	private boolean isUserExist(User user) {
		return userRespository.findByUsernameAndPassword(user.getUsername(), user.getPassword()) != null;
	}

}
