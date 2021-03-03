package com.danielqueiroz.madqueenserver.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "artistcuriosity")
public class ArtistCuriosity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "artist_id")
	private Artist artist;

	private String description;
	
	public ArtistCuriosity() {}

	public ArtistCuriosity(Long id, Artist artist, String description) {
		super();
		this.id = id;
		this.artist = artist;
		this.description = description;
	}

	public ArtistCuriosity(Artist artist, String description) {
		super();
		this.artist = artist;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
