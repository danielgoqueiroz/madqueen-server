//package com.danielqueiroz.madqueenserver.model;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.danielqueiroz.madqueenserver.constants.RoleCons;
//
//public class MyUserDetails implements UserDetails {
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	private User user;
//	
//	private List<SimpleGrantedAuthority> autorities;
//	
//	public MyUserDetails() {
//		super();
//	}
//
//	public MyUserDetails(User user) {
//		this.user = user;
//	}
//	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		List<SimpleGrantedAuthority> autorities = new ArrayList<SimpleGrantedAuthority>();
//		autorities.add(new SimpleGrantedAuthority(RoleCons.USER));
//		return autorities;
//	}
//
//	@Override
//	public String getPassword() {
//		return user.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		return user.getUsername();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
//
//	public List<SimpleGrantedAuthority> getAutorities() {
//		return autorities;
//	}
//
//	public void setAutorities() {
//		this.autorities = getAutorities();
//	}
//
//}
