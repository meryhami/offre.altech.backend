/**
 * Exception thrown when a user is not an adult.
 * This exception is used to return an HTTP status code of 403 (FORBIDDEN)
 * to the client when a user attempts to perform an action that is not allowed
 * due to their age.
 *
 * @author MHAMI Meryem
 * @version 1.0
 */
package com.alltech.offre.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "User is not an adult")
public class UserNotAdultException extends RuntimeException {
	/**
	 * Creates a new UserNotAdultException with the specified message.
	 *
	 * @param message the message to include in the exception
	 */
	public UserNotAdultException(String message) {
		super(message);
	}
}
