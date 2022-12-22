/**
 * A custom exception representing the scenario when a user is not a French resident.
 * This exception is thrown when an operation requiring the user to be a French resident is requested,
 * and the user is not a French resident.
 *
 * @author HAMI Meryem
 */
package com.alltech.offre.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "User is not a french resident")
public class UserNotFrenchResidentException extends RuntimeException {
	/*
	 * Constructs a new exception with the specified detail message.
	 *
	 * @param message the detail message. The detail message is saved for later
	 * retrieval by the Copy code {@link #getMessage()} method.
	 */
	public UserNotFrenchResidentException(String message) {
		super(message);
	}
}
