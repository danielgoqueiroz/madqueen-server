package com.danielqueiroz.madqueenserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danielqueiroz.madqueenserver.model.ArtistCuriosity;

@Repository
public interface ArtistCuriosityRepository extends JpaRepository<ArtistCuriosity, Long>{
	
}
