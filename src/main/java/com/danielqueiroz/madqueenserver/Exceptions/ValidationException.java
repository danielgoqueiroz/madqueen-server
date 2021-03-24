package com.danielqueiroz.madqueenserver.Exceptions;

public class ValidationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ValidationException(String message) {
		super();
		this.message = message;
	}
	
	
	
}
