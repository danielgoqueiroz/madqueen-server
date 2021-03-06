package com.danielqueiroz.madqueenserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.danielqueiroz.madqueenserver.model.MyUserDetails;
import com.danielqueiroz.madqueenserver.model.User;
import com.danielqueiroz.madqueenserver.repository.UserRespository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRespository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}

		MyUserDetails myUserDetails = new MyUserDetails(user);
		myUserDetails.setAutorities();
		return myUserDetails;
	}

}
