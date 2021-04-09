package com.danielqueiroz.madqueenserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danielqueiroz.madqueenserver.model.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>{

	List<Artist> findByName(String name);
	
}
