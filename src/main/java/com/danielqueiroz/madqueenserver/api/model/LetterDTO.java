package com.danielqueiroz.madqueenserver.api.model;

public class LetterDTO {

	private String lang;
	private String letter;

	public LetterDTO() {
		super();
	}

	public LetterDTO(String letter, Integer lang) {
		switch (lang) {
		case 1:
			this.lang = "BR";
			break;
		case 2:
			this.lang = "EN";
			break;
		default:
			break;
		}
		this.letter = letter;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

}
