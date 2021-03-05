package com.danielqueiroz.madqueenserver.repository;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.danielqueiroz.madqueenserver.model.Artist;
import com.danielqueiroz.madqueenserver.model.Band;
import com.danielqueiroz.madqueenserver.model.Music;

@SpringBootTest
public class MusicRepositoryTest {
	
	@Autowired
	private MusicRepository repository;
	
	@Test
	@Transactional
	public void shouldSaveBand() {
		Band band = new Band("Banda", "Descrição");
		Artist artist = new Artist("Manu", "Descrição");
		Music music = new Music("Título", 2020, "Olá Olá Olá", "Hello hello hello", "http://www.youtube.com/video","http://www.linkimagem.com/imagem.jpg", artist, band);
		
		repository.save(music);
	} 

}
