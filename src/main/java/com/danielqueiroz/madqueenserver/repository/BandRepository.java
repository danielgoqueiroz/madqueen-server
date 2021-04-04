package com.danielqueiroz.madqueenserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielqueiroz.madqueenserver.model.Band;

public interface BandRepository extends JpaRepository<Band, Long>{

	Band findByName(String name);

}
