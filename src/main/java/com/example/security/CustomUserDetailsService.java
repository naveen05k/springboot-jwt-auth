/**
 * 
 */
package com.example.security;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.model.UserMaster;
import com.example.repository.UserRepository;

/**
 * @author HP Apr 27, 2025
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserMaster user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

		return new User(user.getUsername(), user.getPassword(),
				user.getRoles().stream().map(
						role -> new org.springframework.security.core.authority.SimpleGrantedAuthority(role.getName()))
						.collect(Collectors.toList()));
	}
}