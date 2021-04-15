package com.danielqueiroz.madqueenserver.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "artist")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Artist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;

	@OneToMany(mappedBy = "artist", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<Music> musics;

	@OneToMany(mappedBy = "artist", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<ArtistCuriosity> curiosities;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name="artistband", joinColumns= { @JoinColumn(name="artist_id")}, inverseJoinColumns=  {@JoinColumn(name="band_id")})
	private List<Band> bands;

	public Artist() {
		this.description = "";
	}

	public Artist(String name, String description) {
		this.name = name;
		this.description = description;
	}


	public Artist(Long id, String name, String description, List<Music> musics, List<ArtistCuriosity> curiosities,
			List<Band> bands) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.musics = musics;
		this.curiosities = curiosities;
		this.bands = bands;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ArtistCuriosity> getCuriosities() {
		if (this.curiosities == null) {
			this.curiosities = new ArrayList<ArtistCuriosity>();
		}
		return curiosities;
	}

	public void setCuriosities(List<ArtistCuriosity> curiosities) {
		this.curiosities = curiosities;
	}

	public void addCuriotity(ArtistCuriosity curiosity) {
		getCuriosities().add(curiosity);
	}

	public List<Music> getMusics() {
		
		return musics;
	}

	public void setMusics(List<Music> musics) {
		this.musics = musics;
	}

	public List<Band> getBands() {
		return bands;
	}

	public void setBands(List<Band> bands) {
		this.bands = bands;
	}

	public void addBand(Band band) {
		if (this.bands == null ) {
			bands = new ArrayList<>();
		}
		this.bands.add(band);
	}

	public void addCuriosity(ArtistCuriosity artistCuriosity) {
		if (this.curiosities == null ) {
			this.curiosities = new ArrayList<ArtistCuriosity>();
		}
		if (artistCuriosity.getArtist() == null) {
			artistCuriosity.setArtist(this);
		}
		curiosities.add(artistCuriosity);
		
	}

	public void addMusic(Music music) {
		if (this.musics == null) {
			this.musics = new ArrayList<>();
		}
		this.musics.add(music);
	}

}
