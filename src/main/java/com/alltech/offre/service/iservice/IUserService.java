/**
 * This interface provides methods for managing users.
 * It includes methods for creating, finding and listing users.
 *
 * @author HAMI Meryem
 */
package com.alltech.offre.service.iservice;

import com.alltech.offre.dto.UserDto;
import com.alltech.offre.exceptions.UserAlreadyExistException;
import com.alltech.offre.exceptions.UserNotFoundException;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface IUserService {

	/**
	 * Creates a new user.
	 *
	 * @param userDto an object containing the user's information
	 * @return the created user
	 * @throws UserAlreadyExistException if the user already exists
	 */
	UserDto create(@NotNull UserDto userDto) throws UserAlreadyExistException;

	/**
	 * Finds a user by their username.
	 *
	 * @param username the user's username
	 * @return the found user
	 * @throws UserNotFoundException if the user is not found
	 */
	UserDto findByUserName(String username) throws UserNotFoundException;

	/**
	 * Lists all the users.
	 *
	 * @return a list of all the users
	 */
	List<UserDto> find();
}
