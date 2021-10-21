package com.codenation.group3.centralDeErros.dtos;

import lombok.Data;

@Data
public class fullErrorLogDTO {
	
	private Long id;
	private String level;
    private String description;
    private String log;
    private String origin;

}
