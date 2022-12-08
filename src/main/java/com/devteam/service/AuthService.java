package com.devteam.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.devteam.dto.LoginDto;
import com.devteam.dto.LoginResponseDto;
import com.devteam.dto.RegisterDto;
import com.devteam.entity.Role;

public interface AuthService {
	ResponseEntity<LoginResponseDto> login(LoginDto dto);

	ResponseEntity<String> register(RegisterDto dto);

	void createRole(Role role);

	Optional<Role> findByRole(String roleType);

	void addRoleToUser(String email, String roleType);
}
