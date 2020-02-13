package com.example.exception;

public class ResourceNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4160700056908309275L;
	private String message;

	public ResourceNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	public ResourceNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
