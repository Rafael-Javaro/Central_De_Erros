package com.codenation.group3.centralDeErros.exceptions;

public class LogWrongDateFormatException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public LogWrongDateFormatException() {
		super("Both 'from' and 'to' date formats must be 'YYYY-MM-DD'.");
	}

}
