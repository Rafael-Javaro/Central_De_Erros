package com.codenation.group3.centralDeErros.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LogIncompleteBodyAdvice {
	
	@ResponseBody
	@ExceptionHandler(LogIncompleteBodyException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String userNotFoundHandler(LogIncompleteBodyException e) {
		return e.getMessage();
	}

}
