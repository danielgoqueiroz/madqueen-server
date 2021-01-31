package com.danielqueiroz.madqueenserver.model;

import java.io.Serializable;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String from;
	private String subject;
	private String message;
	
	public Message() {
		super();
	}
	
	
	public Message(String from, String subject, String message) {
		super();
		this.from = from;
		this.subject = subject;
		this.message = message;
	}


	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
