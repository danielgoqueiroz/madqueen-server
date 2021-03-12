//package com.danielqueiroz.madqueenserver.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.danielqueiroz.madqueenserver.model.MyUserDetails;
//import com.danielqueiroz.madqueenserver.model.User;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//	@Autowired
//    private UserService userService;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userService.findByUsername(username);
//		
//		if (user.getUsername().equals(username)) {
//			return new MyUserDetails(user);
//		} else {
//			throw new UsernameNotFoundException("User not found with email: " + username);
//		}
//	}
//
//}
