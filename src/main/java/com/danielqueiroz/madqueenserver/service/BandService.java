package com.danielqueiroz.madqueenserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielqueiroz.madqueenserver.model.Band;
import com.danielqueiroz.madqueenserver.repository.BandRepository;

@Service
public class BandService {

	@Autowired
	private BandRepository repository;
	
	public List<Band> getBands() {
		return repository.findAll();
	};
	
}
