package com.danielqueiroz.madqueenserver.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.danielqueiroz.madqueenserver.model.User;
import com.danielqueiroz.madqueenserver.service.UserService;

@Service
public class ApplicationUserService implements UserDetailsService {

	private final UserService userService;
	
	@Autowired
	public ApplicationUserService(UserService userService){
		this.userService = userService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException(String.format("Usuário %s não encontrado", username));
		}
		
		ApplicationUserDetails applicationUserDetails = new ApplicationUserDetails(
				user.getAuthorities(), 
				user.getPassword(), 
				user.getUsername(),
				true, true, true, true);
		return applicationUserDetails;
	}
	
}
