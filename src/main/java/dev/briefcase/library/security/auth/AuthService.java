package dev.briefcase.library.security.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.briefcase.library.error.exception.NotFoundException;
import dev.briefcase.library.security.auth.dto.AuthResponse;
import dev.briefcase.library.security.auth.dto.LoginRequest;
import dev.briefcase.library.security.auth.dto.RegisterRequest;
import dev.briefcase.library.security.auth.entity.AuthUser;
import dev.briefcase.library.security.auth.entity.Role;
import dev.briefcase.library.security.jwt.JwtService;

@Service
public class AuthService {

	private final AuthUserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authManager;

	public AuthService(AuthUserRepository repository, PasswordEncoder passwordEncoder, JwtService service,
			AuthenticationManager authManager) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = service;
		this.authManager = authManager;
	}

	public AuthResponse login(LoginRequest request) {

		authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		AuthUser user = repository.findByUsername(request.getUsername())
				.orElseThrow(() -> new NotFoundException("User not found!"));
		//final String token = jwtService.getToken(user);
	
		return AuthResponse.builder()
				.token(jwtService.getToken(user))
				.build();
	}

	public AuthResponse register(RegisterRequest request) {

		AuthUser authUser = AuthUser.builder()
				.username(request.getUsername())
				.password(passwordEncoder.encode(request.getPassword()))
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.country(request.getCountry())
				.role(Role.USER)
				.build();
		
		repository.save(authUser);
		
		return AuthResponse.builder()
				.token(jwtService.getToken(authUser))
				.build();
	}

}
