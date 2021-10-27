package com.codenation.group3.centralDeErros.exceptions;

public class UserIncompleteBodyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserIncompleteBodyException() {
		super("Incomplete body: must have 'name', 'email' and 'password'. ");
	}

}