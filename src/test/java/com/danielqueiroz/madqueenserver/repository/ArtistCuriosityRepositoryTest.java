package com.danielqueiroz.madqueenserver.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.danielqueiroz.madqueenserver.model.Artist;
import com.danielqueiroz.madqueenserver.model.ArtistCuriosity;

@SpringBootTest
public class ArtistCuriosityRepositoryTest {
	
	@Autowired
	private ArtistCuriosityRepository repository;
	
	@Test
	@Transactional
	public void shouldSaveCuriosity() {
		
		Artist artist = new Artist("Manu", "Cantora");
		ArtistCuriosity curiosity= new ArtistCuriosity(artist, "Descrição da curiosidade");
		ArtistCuriosity curiositySaved= repository.save(curiosity);
		
		assertNotNull(curiositySaved.getId());
		assertNotNull(curiositySaved.getDescription());
		
		Artist artistSaved = curiositySaved.getArtist();
		assertNotNull(artistSaved);
		assertNotNull(artistSaved.getId());
		assertNotNull(artistSaved.getDescription());
	} 
	
}
