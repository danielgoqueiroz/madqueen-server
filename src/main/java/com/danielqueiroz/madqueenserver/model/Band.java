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
@Table(name = "band")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Band {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private int created;

	@OneToMany(mappedBy = "band", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<Music> musics;

    @ManyToMany
    @JoinTable(name="artistband", joinColumns= { @JoinColumn(name="artist_id")}, inverseJoinColumns=  {@JoinColumn(name="band_id")})
	private List<Artist> artists;

	public Band() {
	}

	public Band(Long id, String name, String description, int created, List<Music> musics, List<Artist> artists) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.created = created;
		this.musics = musics;
		this.artists = artists;
	}
	
	public Band(String name, String description) {
		this.name = name;
		this.description = description;
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

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public List<Music> getMusics() {
		if (this.musics == null) {
			musics = new ArrayList<>();
		}
		return musics;
	}

	public void setMusics(List<Music> musics) {
		this.musics = musics;
	}

	public List<Artist> getArtists() {
		if (this.artists== null) {
			artists = new ArrayList<>();
		}
		return artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

}
