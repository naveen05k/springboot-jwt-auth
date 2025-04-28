/**
 * 
 */
package com.example.payload;

import java.util.Set;

import lombok.Data;

/**
 * @author HP Apr 28, 2025
 */
@Data
public class RegisterRequest {
	private String name;
	private String username;
	private String email;
	private String password;
	private Set<String> roles; // Role names like "ROLE_USER", "ROLE_ADMIN"
}
