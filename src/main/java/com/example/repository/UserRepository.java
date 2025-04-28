/**
 * 
 */
package com.example.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.UserMaster;

/**
 * @author HP Apr 27, 2025
 */

@Repository
public interface UserRepository extends JpaRepository<UserMaster, Serializable> {
	Optional<UserMaster> findByEmail(String email);

	Optional<UserMaster> findByName(String name);

	Optional<UserMaster> findByUsername(String username);

	/**
	 * @param username
	 * @return
	 */
}
