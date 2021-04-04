package com.danielqueiroz.madqueenserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielqueiroz.madqueenserver.Exceptions.ValidationException;
import com.danielqueiroz.madqueenserver.model.Band;
import com.danielqueiroz.madqueenserver.repository.BandRepository;
import com.google.common.base.Strings;

@Service
public class BandService {

	@Autowired
	private BandRepository repository;
	
	public List<Band> getBands() {
		return repository.findAll();
	}

	public Band save(Band band) throws ValidationException {
		validate(band);
		if (band.getDescription() == null) {
			band.setDescription(band.getName());
		}
		Band save = repository.save(band);
		return save;
	}

	private void validate(Band band) throws ValidationException {
		if (Strings.isNullOrEmpty(band.getName())) {
			throw new ValidationException("O nome da banda precisa ser preenchido.");
		}
		if (band.getName().length() <= 2) {
			throw new ValidationException("O nome da banda é muito curto.");
		}
		if (band.getId() !=null) {
			throw new ValidationException("O id não pode estar preenchido");
		}
		Band findByName = repository.findByName(band.getName());
		if (findByName != null) {
			throw new ValidationException("O nome da banda já existe.");
		}
	};
	
}
