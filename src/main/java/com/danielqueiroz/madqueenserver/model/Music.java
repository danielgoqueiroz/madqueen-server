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
@Table(name = "music")
public class Music {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title ;
	private int cratedYear;
	private String letterOriginal; 
	private String letterTranslation;
	private String youtubeLink;
	private String imageLink;
	
	@ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "artist_id")
	private Artist artist;
	
	@ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "band_id")
	private Band band;
	
	public Music() {
	}
	
	public Music(String title, int cratedYear, String letterOriginal, String letterTranslation,
			String youtubeLink, String imageLink, Artist artist, Band band) {
		this.title = title;
		this.cratedYear = cratedYear;
		this.letterOriginal = letterOriginal;
		this.letterTranslation = letterTranslation;
		this.youtubeLink = youtubeLink;
		this.imageLink = imageLink;
		this.artist = artist;
		this.band = band;
	}
	
	public Music(String title, int cratedYear, Artist artist, Band band) {
		this.title = title;
		this.cratedYear = cratedYear;
		this.artist = artist;
		this.band = band;
	}
	
	public Music(Long id, String title, int cratedYear, String letterOriginal, String letterTranslation,
			String youtubeLink, String imageLink, Artist artist, Band band) {
		super();
		this.id = id;
		this.title = title;
		this.cratedYear = cratedYear;
		this.letterOriginal = letterOriginal;
		this.letterTranslation = letterTranslation;
		this.youtubeLink = youtubeLink;
		this.imageLink = imageLink;
		this.artist = artist;
		this.band = band;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCratedYear() {
		return cratedYear;
	}
	public void setCratedYear(int cratedYear) {
		this.cratedYear = cratedYear;
	}
	public String getLetterOriginal() {
		return letterOriginal;
	}
	public void setLetterOriginal(String letterOriginal) {
		this.letterOriginal = letterOriginal;
	}
	public String getLetterTranslation() {
		return letterTranslation;
	}
	public void setLetterTranslation(String letterTranslation) {
		this.letterTranslation = letterTranslation;
	}
	public String getYoutubeLink() {
		return youtubeLink;
	}
	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	public Band getBand() {
		return band;
	}
	public void setBand(Band band) {
		this.band = band;
	}
	
	

}
