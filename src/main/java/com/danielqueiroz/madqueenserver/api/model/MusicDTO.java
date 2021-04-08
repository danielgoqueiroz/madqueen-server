package com.danielqueiroz.madqueenserver.api.model;

import java.util.ArrayList;
import java.util.List;

public class MusicDTO {

	private String id;
	private String title;
	private String band;
	private List<LetterDTO> Letters;

	public MusicDTO() {
		super();
	}

	public MusicDTO(String id, String title, String band) {
		super();
		this.id = id;
		this.title = title;
		this.band = band;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public List<LetterDTO> getLetters() {
		if (Letters == null) {
			Letters = new ArrayList<>();
		}
		return Letters;
	}

	public void setLetters(List<LetterDTO> letters) {
		Letters = letters;
	}

}
