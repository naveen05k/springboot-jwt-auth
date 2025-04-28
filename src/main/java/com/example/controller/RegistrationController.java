/**
 * 
 */
package com.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payload.RegisterRequest;
import com.example.service.UserMasterService;

/**
 * @author HP Apr 28, 2025
 */
@RestController
@RequestMapping("/api/auth")
public class RegistrationController {

	private final UserMasterService userMasterService;

	public RegistrationController(UserMasterService userMasterService) {
		this.userMasterService = userMasterService;
	}

	@PostMapping("/register")
	public String register(@RequestBody RegisterRequest registerRequest) {
		return userMasterService.registerUser(registerRequest);
	}
}