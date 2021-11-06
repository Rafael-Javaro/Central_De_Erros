package com.codenation.group3.centralDeErros.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorLogDTO {
	
	private Long id;
	private String level;
    private String description;
    private String origin;

}
