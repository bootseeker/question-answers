package com.example.exception;

public class ResourceExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1638321715767558288L;
	String message;

	public ResourceExistsException(String message) {
		super(message);
		this.message = message;
	}

	public ResourceExistsException() {
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
