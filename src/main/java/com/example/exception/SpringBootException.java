package com.example.exception;

import java.util.Vector;

public class SpringBootException extends RuntimeException 
{

	private String errorMessage;
	private Vector errorMessageList;
	
	public SpringBootException(String errorMessageArg) 
	{
		super();
		errorMessageList = new Vector();
		errorMessageList.add(errorMessageArg);
	}
	
	public SpringBootException(Vector errorMessageListArg) {
		super();
		this.errorMessageList = errorMessageListArg;
	}

	public String getErrorMessage() 
	{
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) 
	{
		this.errorMessage = errorMessage;
	}

	public Vector getErrorMessageList() {
		return errorMessageList;
	}

	public void setErrorMessageList(Vector errorMessageList) {
		this.errorMessageList = errorMessageList;
	}
	
}
