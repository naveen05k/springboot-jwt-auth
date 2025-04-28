/**
* 
*/
package com.example.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author HP Apr 28, 2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthResponseRequest {

	private String accessToken;
	private String refreshToken;

}