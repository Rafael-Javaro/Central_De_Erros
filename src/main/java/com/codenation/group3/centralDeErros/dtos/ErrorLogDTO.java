package com.codenation.group3.centralDeErros.dtos;

import lombok.Data;

@Data
public class ErrorLogDTO {
	
	private Long id;
	private String level;
    private String description;
    private String log;
    private String origin;

}
