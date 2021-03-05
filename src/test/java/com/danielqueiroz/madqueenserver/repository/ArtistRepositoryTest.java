package com.danielqueiroz.madqueenserver.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.danielqueiroz.madqueenserver.model.Artist;
import com.danielqueiroz.madqueenserver.model.ArtistCuriosity;
import com.danielqueiroz.madqueenserver.model.Band;
import com.danielqueiroz.madqueenserver.model.Music;

@SpringBootTest
public class ArtistRepositoryTest {
	
	@Autowired
	private ArtistRepository repository;
	
	@Test
	@Transactional
	public void shouldSaveCompleteArtist() {
		
		Artist artist = new Artist("Manu", "Cantora");
		
		Band band = new Band("MadQueen", "Banda de rock");
		artist.addBand(band);
		
		artist.addCuriosity(new ArtistCuriosity("Descrição da curiosidade"));
		artist.addMusic(new Music("Música", 2021, new Artist("Dio", "Dio"), new Band("Dio Banda", "Banda dio")));
		
		Artist artistSaved = repository.save(artist);
		assertNotNull(artistSaved);
		assertNotNull(artistSaved.getId());
		
		assertTrue(!artistSaved.getCuriosities().isEmpty());
		assertTrue(!artistSaved.getBands().isEmpty());
		assertTrue(!artistSaved.getMusics().isEmpty());
		
		assertNotNull(artistSaved.getMusics().get(0).getId());
		assertNotNull(artistSaved.getBands().get(0).getId());
		
		Artist artistFinded = repository.findByName("Manu");
		assertNotNull(artistFinded);
		assertNotNull(artistFinded.getName());
		assertEquals(artist.getName(), artistFinded.getName());
		
	}
	
	@Test
	@Transactional
	public void shouldGetArtistByNameWithoutCuriosity() {
		
		Artist artist = new Artist("Manu", "Cantora");
		
		Artist artistSaved = repository.save(artist);
		assertNotNull(artistSaved);
		assertNotNull(artistSaved.getId());
		
		assertTrue(artistSaved.getCuriosities().isEmpty());
		
		Artist artistFinded = repository.findByName("Manu");
		assertNotNull(artistFinded);
		assertNotNull(artistFinded.getName());
		assertEquals(artist.getName(), artistFinded.getName());
		
	}
	
	@Test
	@Transactional
	public void shouldGetArtistByNameWithCuriosity() {
		
		Artist artist = new Artist("Manu", "Cantora");
		ArtistCuriosity curiosity = new ArtistCuriosity(artist, "Descrição da curiosidade");
		artist.addCuriotity(curiosity);
		
		Artist artistSaved = repository.save(artist);
		assertNotNull(artistSaved);
		assertNotNull(artistSaved.getId());
		
		assertTrue(!artistSaved.getCuriosities().isEmpty());
		
		Artist artistFinded = repository.findByName("Manu");
		assertNotNull(artistFinded.getId());
		assertNotNull(artistFinded);
		assertNotNull(artistFinded.getName());
		assertEquals(artist.getName(), artistFinded.getName());
		
	}
	
}
