package com.danielqueiroz.madqueenserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielqueiroz.madqueenserver.model.Artist;
import com.danielqueiroz.madqueenserver.repository.ArtistRepository;


@Service
public class ArtistService {
	
	@Autowired
	private ArtistRepository repository;
	
	public List<Artist> getAll() {
		List<Artist> findAll = repository.findAll();
		return findAll;
	}

}
