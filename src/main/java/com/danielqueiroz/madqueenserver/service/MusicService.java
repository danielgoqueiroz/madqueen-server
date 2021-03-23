package com.danielqueiroz.madqueenserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielqueiroz.madqueenserver.model.Music;
import com.danielqueiroz.madqueenserver.repository.MusicRepository;

@Service
public class MusicService {

	@Autowired
	private MusicRepository repository;
	
	 public List<Music> getMusics() {
		 
		 List<Music> findAll = repository.findAll();
		 
		 return findAll;
	 }
	
}
