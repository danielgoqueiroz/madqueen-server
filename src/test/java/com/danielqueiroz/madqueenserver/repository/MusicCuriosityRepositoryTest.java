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
public class MusicCuriosityRepositoryTest {
	
	@Autowired
	private MusicRepository repository;
	
	@Test
	@Transactional
	public void shouldSaveMusicCuriosity() {
		
		Band band = new Band("Banda", "Descrição");
		Artist artist = new Artist("Manu", "Descrição");
		
		Music music = new Music(
				"Título", 
				2020, 
				"Olá Olá Olá", 
				"Hello hello hello", 
				"http://www.youtube.com/video",
				"http://www.linkimagem.com/imagem.jpg", 
				artist, 
				band);
		
		Music save = repository.save(music);
		
		assertNotNull(save);
		assertNotNull(save.getId());
		assertNotNull(save.getArtist().getId());
		assertNotNull(save.getBand().getId());
		
	} 
	
}
