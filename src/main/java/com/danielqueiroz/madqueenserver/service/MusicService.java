package com.danielqueiroz.madqueenserver.service;

import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielqueiroz.madqueenserver.Exceptions.ValidationException;
import com.danielqueiroz.madqueenserver.api.VagalumeAPI;
import com.danielqueiroz.madqueenserver.api.model.MusicDTO;
import com.danielqueiroz.madqueenserver.model.Music;
import com.danielqueiroz.madqueenserver.repository.MusicRepository;
import com.google.common.base.Strings;

@Service
public class MusicService {

	@Autowired
	private MusicRepository repository;
	
	@Autowired
	private VagalumeAPI api;
	
	 public List<Music> getMusics(String title) {

		 if (!Strings.isNullOrEmpty(title)) {
			 List<Music> asList = Arrays.asList(repository.findMusicByTitle(title));
			return asList;
		 }
		 
		 List<Music> findAll = repository.findAll();
		 return findAll;
	 }

	public void save(Music music) throws ValidationException {

		validade(music);
		
		Music findMusicByTitle = repository.findMusicByTitle(music.getTitle());
		
		if (findMusicByTitle == null ) {
			repository.save(music);
		} else {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
		
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

	public List<MusicDTO> searchMusics(String title) throws ValidationException, JSONException {
		if (Strings.isNullOrEmpty(title) ) {
			throw new ValidationException("Deve ser passado uma títiulo para a pesquisa.");
		}
		List<MusicDTO> musics = api.getMusics(title);
		return musics;
	}
	
}
