package com.codenation.group3.centralDeErros.exceptions;

public class LogIncompleteBodyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public LogIncompleteBodyException() {
		super("Incomplete body: must have 'level', 'description', 'log' and 'origin'. ");
	}

}
