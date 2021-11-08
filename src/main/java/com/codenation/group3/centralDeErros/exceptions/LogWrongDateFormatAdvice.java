package com.codenation.group3.centralDeErros.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LogWrongDateFormatAdvice {
	
	@ResponseBody
	@ExceptionHandler(LogWrongDateFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String userNotFoundHandler(LogWrongDateFormatException e) {
		return e.getMessage();
	}

}
