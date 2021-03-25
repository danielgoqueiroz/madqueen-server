package com.danielqueiroz.madqueenserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danielqueiroz.madqueenserver.model.Music;

@Repository
public interface  MusicRepository extends JpaRepository<Music, Long>{

	Music findByTitle(String title);

}
