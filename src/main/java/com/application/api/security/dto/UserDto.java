package com.application.api.security.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDto {
	private Long userId;
	private String username;
	private String password;
	private Boolean userNonExpired;
	private Boolean userNonLocked;
	private Boolean userCredentialsNonExpired;
	private Boolean userEnabled;
	private List<String> authorities;
}
