/**
 * Repository interface for {@link UserEntity} objects. Provides basic CRUD operations
 * as well as some additional methods to find and check the existence of {@link UserEntity} objects.
 *
 * @author HAMI Meryem
 */
package com.alltech.offre.repository;

import com.alltech.offre.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

	/**
	 * Check if a {@link UserEntity} with the given username exists in the
	 * repository.
	 *
	 * @param username the username to check for
	 * @return true if a {@link UserEntity} with the given username exists, false
	 * otherwise
	 */
	boolean existsByUserName(String username);

	/**
	 * Find the {@link UserEntity} with the given username.
	 *
	 * @param username the username of the {@link UserEntity} to find
	 * @return an {@link Optional} containing the found {@link UserEntity}, or an
	 * empty {@link Optional} if no such {@link UserEntity} exists
	 */
	Optional<UserEntity> findByUserName(String username);
}
