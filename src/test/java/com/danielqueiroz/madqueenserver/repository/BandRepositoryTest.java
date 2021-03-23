package com.danielqueiroz.madqueenserver.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.danielqueiroz.madqueenserver.model.Artist;
import com.danielqueiroz.madqueenserver.model.Band;
import com.danielqueiroz.madqueenserver.model.Music;

@SpringBootTest
public class BandRepositoryTest {
	
	@Autowired
	private BandRepository repository; 
	
	@Test
	@Transactional
	public void shouldSave() {
		
		Band band = new Band("banda teste", "Dexcrição banda teste");
		band.setCreated(2021);
		
		Band saved = repository.save(band);
		
		assertNotNull(saved);
		assertNotNull(saved.getId());
		
	} 
	
	@Test
	@Transactional
	public void shouldSavebandWithMusic() {
		
		Band band = new Band("banda teste", "Descrição banda teste");
		band.setCreated(2021);
		
		Artist artist = new Artist("Artista Teste", "Artista descrição");
		
		band.getArtists().add(artist);
		
		Music music = new Music("Título música", 2020, artist, band);
		band.getMusics().add(music);
		
		Band saved = repository.save(band);
		
		assertNotNull(saved);
		assertNotNull(saved.getId());
		
	} 
}
