package com.danielqueiroz.madqueenserver.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.danielqueiroz.madqueenserver.constants.RoleCons;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "user")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String username;

	@Column(unique = true)
	private String email;

	private String password;

	private String cpf;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "userrole", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<Role>();

	public User() {
	}
	
	public User(UserDTO user) {
		super();
		this.username  =user.getUsername();
		this.password = user.getPassword();
		this.cpf  =user.getCpf();
		this.email = user.getEmail();
		addRole(new Role(RoleCons.USER));
	}

	public User(String name, String password, String email, String cpf, List<Role> roles) {
		this.email = email;
		this.username = name;
		this.password = password;
		this.cpf = cpf;
		this.roles = roles;
	}

	public User(String name, String password, String email, String cpf, Role role) {
		this.email = email;
		this.username = name;
		this.password = password;
		this.cpf = cpf;
		addRole(role);
	}
	
	public User(String name, String password, String email, String cpf) {
		this.email = email;
		this.username = name;
		this.password = password;
		this.cpf = cpf;
		addRole(new Role(RoleCons.USER));
	}
	
	public User(String name, String password, Role role) {
		this.username = name;
		this.password = password;
		addRole(role);
	}
	
	public User(String name, String password) {
		this.username = name;
		this.password = password;
		addRole(new Role(RoleCons.USER));
	}

	private void addRole(Role role) {
		if (this.roles == null) {
			this.roles = new ArrayList<>();
		}
		this.roles.add(role);

	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}
	
	public void setUsername(final String name) {
		this.username = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		User user = (User) o;
		return Objects.equals(id, user.id);
	}

	public List<Role> getRoles() {
		return roles;
	}
	
	public List<SimpleGrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getDescription())).collect(Collectors.toList());
		return authorities;
	}

	public void setAddRoleTypeUser() {
		if (this.roles == null || this.roles.isEmpty()) {
			this.roles = new ArrayList<Role>();
		}
		this.roles.add(new Role("USER"));
		this.roles = getRoles();
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void addDefaultRole() {
		if (this.getRoles() == null || this.getRoles().isEmpty()) {
			this.roles = new ArrayList<Role>();
		}
		Role rl = new Role("USER");
		this.getRoles().add(rl);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
