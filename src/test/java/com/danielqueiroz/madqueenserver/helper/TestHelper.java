package com.danielqueiroz.madqueenserver.helper;

import com.danielqueiroz.madqueenserver.model.Artist;
import com.danielqueiroz.madqueenserver.model.Band;
import com.danielqueiroz.madqueenserver.model.Music;

public class TestHelper {

	public static Artist getArtist() {
		return new Artist("Artista Teste", "Descrição artísta teste.");
	}
	
	public static Music getMusic() {
		return new Music(
				"Título Música", 
				2020, 
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris justo ante, lacinia sed dolor vel, commodo tincidunt libero. Aenean in lobortis elit, eu ultrices augue. Ut interdum urna in mi accumsan suscipit euismod in justo. Sed luctus sit amet dui ut convallis. Praesent bibendum diam eget iaculis sagittis. Nullam finibus tincidunt tempor. Ut mattis rutrum augue at pulvinar. Fusce vitae feugiat ligula. Morbi vestibulum iaculis nibh at luctus. Fusce quis rhoncus justo. Nulla malesuada ex sem, eget condimentum mi feugiat nec. Duis at dolor neque. In hac habitasse platea dictumst. Aenean tristique varius leo, sed bibendum magna fermentum non. Nam porta efficitur felis quis ultrices.", null, "https://www.youtube.com/watch?v=CX5HaDYTu7s", "https://img.freepik.com/fotos-gratis/3d-rendem-de-uma-mesa-de-madeira-com-uma-imagem-defocussed-de-um-barco-em-um-lago_1048-3432.jpg?size=626&ext=jpg", 
				getArtist(), 
				getBand());
	}

	public static Band getBand() {
		return new Band("Nome da Banda", "Descriçao da banda");
	}
	
}