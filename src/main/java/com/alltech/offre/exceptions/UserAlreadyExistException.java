/**
 * This class represents a custom exception that is thrown when a user with the same username already exists.
 * It extends the {@link RuntimeException} class and is annotated with the {@link ResponseStatus} annotation to specify
 * that it should return an HTTP status code of 409 (CONFLICT) when this exception is thrown.
 *
 * @author HAMI Meryem
 */
package com.alltech.offre.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Username Already exist")
public class UserAlreadyExistException extends RuntimeException {
	public UserAlreadyExistException(String message) {
		super(message);
	}
}
