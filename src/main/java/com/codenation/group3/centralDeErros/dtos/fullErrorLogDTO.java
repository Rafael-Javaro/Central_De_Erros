package com.codenation.group3.centralDeErros.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class fullErrorLogDTO {
	
	private Long id;
	private String level;
    private String description;
    private String log;
    private String origin;
    private LocalDateTime createdAt;

}
