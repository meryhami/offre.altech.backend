/**
 * A custom exception class that represents a scenario when a user is not found in the system.
 * This exception is thrown when a user with the specified identifier is not found in the database.
 *
 * @author HAMI Meryem
 * @see Exception
 */
package com.alltech.offre.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFoundException extends Exception {
	/**
	 * Constructs a new {@code UserNotFoundException} with the specified message.
	 *
	 * @param message the detail message. The detail message is saved for later
	 *                retrieval by the {@link #getMessage()} method.
	 */
	public UserNotFoundException(String message) {
		super(message);
	}
}
