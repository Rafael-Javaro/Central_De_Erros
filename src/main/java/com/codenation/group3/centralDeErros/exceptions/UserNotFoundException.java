package com.codenation.group3.centralDeErros.exceptions;

public class UserNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(Long id) {
		super("The user associated with the id " + id + " doesn't exist.");
	}

}
