/**
 * 
 */
package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HP Apr 28, 2025
 */

@RestController
public class SampleTestingController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello from secured endpoint!";
	}
}
