package com.codenation.group3.centralDeErros.exceptions;

public class LogNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public LogNotFoundException(Long id) {
		super("The log associated with the id " + id + " doesn't exist.");
	}

}
