package com.danielqueiroz.madqueenserver.service;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielqueiroz.madqueenserver.model.Artist;
import com.danielqueiroz.madqueenserver.repository.ArtistRepository;
import com.google.common.base.Strings;


@Service
public class ArtistService {
	
	@Autowired
	private ArtistRepository repository;
	
	public List<Artist> getAll() {
		List<Artist> findAll = repository.findAll();
		return findAll;
	}

	public boolean save(Artist artist) {
		validate(artist);
		
		Artist findByName = repository.findByName(artist.getName());
		
		if (findByName != null) {
			Artist save = repository.save(artist);
			if (save != null) {
				return true;
			}
		}
		return false;
	}

	private void validate(Artist artist) throws ValidationException {
		
		if (Strings.isNullOrEmpty(artist.getDescription())) {
			throw new ValidationException("A descrição não possui valor válido.");
		}
		if (Strings.isNullOrEmpty(artist.getName())) {
			throw new ValidationException("o nome não possui valor válido.");
		}
		
	}

}
