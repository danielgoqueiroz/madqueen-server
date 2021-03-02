package com.danielqueiroz.madqueenserver.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.danielqueiroz.madqueenserver.model.Artist;

//@Disabled
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ArtistRepositoryTest {
	
	@Autowired
	private ArtistRepository repository;
	
	@Test
	@Transactional
	public void shouldSaveArtist() {
		
		Artist artist = new Artist("Manu", "Cantora");
		
		Artist saved = repository.save(artist);
		assertNotNull(saved);
		assertNotNull(saved.getId());
		
	}

	@Test
	@Transactional
	public void shouldGetArtistByName() {
		
		Artist artist = new Artist("Manu", "Cantora");

		Artist saved = repository.save(artist);
		assertNotNull(saved);
		assertNotNull(saved.getId());
		
		Artist artistFinded = repository.findByArtistName("Manu");
		assertNotNull(artistFinded);
		assertNotNull(artistFinded.getName());
		assertEquals(artist.getName(), artistFinded.getName());
		
	}
	
}
