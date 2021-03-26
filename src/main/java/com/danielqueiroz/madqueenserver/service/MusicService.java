package com.danielqueiroz.madqueenserver.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielqueiroz.madqueenserver.Exceptions.ValidationException;
import com.danielqueiroz.madqueenserver.model.Music;
import com.danielqueiroz.madqueenserver.repository.MusicRepository;
import com.google.common.base.Strings;

@Service
public class MusicService {

	@Autowired
	private MusicRepository repository;
	
	 public List<Music> getMusics(String title) {
		 
		 if (!Strings.isNullOrEmpty(title)) {
			 List<Music> asList = Arrays.asList(repository.findMusicByTitle(title.trim()));
			return asList;
		 }
		 
		 List<Music> findAll = repository.findAll();
		 return findAll;
	 }

	public void save(Music music) throws ValidationException {
		validade(music);
		
	}

	private void validade(Music music) throws ValidationException {
		if (music == null) {
			throw new ValidationException("O valoir de música está nulo.");
		}
		if (music.getArtist() == null) {
			throw new ValidationException("A música precisa ter um artísta vinculado.");
		}
		if (music.getBand() == null) {
			throw new ValidationException("A música precisa ter um uma banda vinculada.");
		}
		
	}
	
}
