package com.codenation.group3.centralDeErros.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDTO {
	
	private Long id;
	private String name;
	private String email;
	private LocalDateTime createdAt;

}
