package com.bookservice.requestdto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequestDto {
	@NotBlank(message = "Username must not be empty")
	private String username;
	@NotBlank(message = "Password must not be empty")
	private String password;

}
