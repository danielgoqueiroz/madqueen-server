package com.danielqueiroz.madqueenserver.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.danielqueiroz.madqueenserver.model.User;
import com.danielqueiroz.madqueenserver.repository.UserRespository;

@Repository
public class ApplicationUserDaoImpl implements ApplicationUserDao {

	@Autowired
	private UserRespository repository;
	
	@Autowired
	private final PasswordEncoder passwordEncoder;
	
	public ApplicationUserDaoImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		User user = repository.findByUsername(username);
		if (user == null) {
			return Optional.empty();
		}
		ApplicationUser applicationUser = new ApplicationUser(user.getAuthorities(), user.getPassword(), user.getUsername(), true, true, true, true);
		return Optional.of(applicationUser);
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}
	
}
