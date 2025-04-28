/**
 * 
 */
package com.example.service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author HP Apr 28, 2025
 */

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.Role;
import com.example.model.UserMaster;
import com.example.payload.RegisterRequest;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;

@Service
public class UserMasterService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public UserMasterService(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public String registerUser(RegisterRequest registerRequest) {
		if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
			return "Username already taken!";
		}

		if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
			return "Email already registered!";
		}

		UserMaster user = new UserMaster();
		user.setName(registerRequest.getName());
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

		Set<Role> roles = new HashSet<>();
		for (String roleName : registerRequest.getRoles()) {
			Role role = roleRepository.findByName(roleName)
					.orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
			roles.add(role);
		}
		user.setRoles(roles);

		user.setCreatedBy("SYSTEM"); // Or you can set logged-in user
		userRepository.save(user);

		return "User Registered Successfully!";
	}
}
