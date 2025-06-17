package com.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.dto.LoginRequestDTO;
import com.movie.dto.LoginResponseDTO;
import com.movie.dto.RegisterRequestDTO;
import com.movie.entity.User;
import com.movie.services.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/registernormaluser")
	public ResponseEntity<User> registerNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
		return ResponseEntity.ok(authenticationService.registerNormalUser(registerRequestDTO));
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
		return ResponseEntity.ok(authenticationService.login(loginRequestDTO));
	}

}
