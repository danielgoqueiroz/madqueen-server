package com.danielqueiroz.madqueenserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielqueiroz.madqueenserver.constants.UserCons;
import com.danielqueiroz.madqueenserver.model.Role;
import com.danielqueiroz.madqueenserver.model.User;
import com.danielqueiroz.madqueenserver.repository.RoleRepository;
import com.danielqueiroz.madqueenserver.repository.UserRespository;
import com.danielqueiroz.madqueenserver.utils.Validation;

@Service
public class UserService {

	private UserRespository userRespository;
	
	private RoleRepository roleRepository;
	

	@Autowired
	public UserService(final UserRespository userRespository, RoleRepository roleRepository) {
		this.userRespository = userRespository;
		this.roleRepository = roleRepository;
	}

	public User findById(Long id) {
		return userRespository.findById(id).get();
	}

	public User findByUsername(String username) {
		return userRespository.findByUsername(username);
	}

	public User findByNameAndPassword(String username, String password) {
		User user = userRespository.findByUsernameAndPassword(username, password);
		user.setPassword(null);
		return user;
	}

	public List<User> findAll() {
		return userRespository.findAll();
	}

	public User save(User user) {
		
		if (Validation.validate(user.getName(), UserCons.NAME_SIZE_MIN, UserCons.NAME_SIZE_MAX)
				&& Validation.validate(user.getPassword(), UserCons.PASSWORD_SIZE_MIN,
						UserCons.PASSWORD_SIZE_MAX)
				&& Validation.validate(user.getEmail(), UserCons.EMAIL_SIZE_MIN, UserCons.EMAIL_SIZE_MAX)
				&& !isUserExist(user)) {
			if (user.getRoles().isEmpty()) {
				user.addDefaultRole();
			}
			List<Role> rolesUpdated = new ArrayList<Role>();
			user.getRoles().forEach(role -> {
				Role roleObj = roleRepository.findByDescription(role.getDescription());
				rolesUpdated.add(roleObj);
			});
			user.setRoles(rolesUpdated);
			return userRespository.save(user);
		}
		return null;
	}

	private boolean isUserExist(User user) {

		return userRespository.findByUsernameAndPassword(user.getName(), user.getPassword()) != null;
	}

	public void deleteById(Long id) {
		userRespository.deleteById(id);
	}

	public void delete(User user) {
		userRespository.delete(user);
	}

}
