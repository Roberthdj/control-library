package dev.briefcase.library.security.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.briefcase.library.security.auth.dto.AuthResponse;
import dev.briefcase.library.security.auth.dto.LoginRequest;
import dev.briefcase.library.security.auth.dto.RegisterRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService service;	
	
	public AuthController(AuthService service) {
		this.service = service;
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(service.login(request));
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(service.register(request));
	}

}
