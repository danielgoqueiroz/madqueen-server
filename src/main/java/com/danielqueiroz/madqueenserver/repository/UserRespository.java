package com.danielqueiroz.madqueenserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danielqueiroz.madqueenserver.model.User;

@Repository
public interface UserRespository extends JpaRepository<User, Long> {
	
	User findByUsernameAndPassword(String name, String password);

	User findByEmail(String email);
	
	User findByUsername(String username);

}
