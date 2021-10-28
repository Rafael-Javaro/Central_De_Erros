package com.codenation.group3.centralDeErros.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(String email) {
		super("The user associated with the email " 
				+ email 
				+ " already exists.");
	}

}