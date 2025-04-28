/**
 * 
 */
package com.example.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payload.AuthRequestDto;
import com.example.payload.AuthResponseRequest;
import com.example.security.JwtUtil;

/**
 * @author HP Apr 28, 2025
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;

	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/login")
	public AuthResponseRequest login(@RequestBody AuthRequestDto request) {
		try {
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
					request.getUsername(), request.getPassword());

			authenticationManager.authenticate(authInputToken); // 🔥 authentication

			String token = jwtUtil.generateToken(request.getUsername()); // 🔥 generate JWT
			String refreshToken = jwtUtil.generateRefreshToken(request.getUsername());

			return new AuthResponseRequest(token, refreshToken);
		} catch (AuthenticationException ex) {
			throw new RuntimeException("Invalid username or password", ex); // 🔥 Better error
		}
	}
}