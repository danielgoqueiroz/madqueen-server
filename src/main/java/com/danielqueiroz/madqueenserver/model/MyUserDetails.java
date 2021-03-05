package com.danielqueiroz.madqueenserver.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {
	
	private User user;
	
	private List<SimpleGrantedAuthority> autorities;
	
	public MyUserDetails(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> autorities = new ArrayList<SimpleGrantedAuthority>();
		autorities.add(new SimpleGrantedAuthority("USER"));
		return autorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public List<SimpleGrantedAuthority> getAutorities() {
		return autorities;
	}

	public void setAutorities() {
		this.autorities = getAutorities();
	}

}
