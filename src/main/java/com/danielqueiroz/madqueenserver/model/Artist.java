package com.danielqueiroz.madqueenserver.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "artist")
public class Artist {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	
    @OneToMany(mappedBy = "artist", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private List<ArtistCuriosity> curiosities;
	
	public Artist() {}
	
	
	public Artist(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public Artist(Long id, String name, String description) {
		this.id = id;
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
	
}
