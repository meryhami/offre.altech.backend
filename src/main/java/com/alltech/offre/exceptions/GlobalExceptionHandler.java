/**
 * This class represents a global exception handler for the application.
 * It is used to handle exceptions that may be thrown during the execution of the application.
 *
 * @author HAMI Meryem
 * @version 1.0
 */
package com.alltech.offre.exceptions;

import com.alltech.offre.dto.ReturnMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Handles the {@link UserNotFoundException} by returning a response with a 404
	 * status code and the exception message.
	 *
	 * @param ex the exception to handle
	 * @return the response with a 404 status code and the exception message
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ReturnMessageDto> handleUserNotFoundException(UserNotFoundException ex) {
		ReturnMessageDto error = new ReturnMessageDto();
		error.setHttpstatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles the {@link UserAlreadyExistException} by returning a response with a
	 * 409 status code and the exception message.
	 *
	 * @param ex the exception to handle
	 * @return the response with a 409 status code and the exception message
	 */
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<ReturnMessageDto> handleUserAlreadyExistException(UserAlreadyExistException ex) {
		ReturnMessageDto error = new ReturnMessageDto();
		error.setHttpstatus(HttpStatus.CONFLICT.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

	/**
	 * Handles the {@link UserNotFrenchResidentException} by returning a response
	 * with a 403 status code and the exception message.
	 *
	 * @param ex the exception to handle
	 * @return the response with a 403 status code and the exception message
	 */
	@ExceptionHandler(UserNotFrenchResidentException.class)
	public ResponseEntity<ReturnMessageDto> handleUserNotFrenchResidentException(UserNotFrenchResidentException ex) {
		ReturnMessageDto error = new ReturnMessageDto();
		error.setHttpstatus(HttpStatus.FORBIDDEN.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

	/**
	 * This method is used to handle an exception type
	 * {@link UserNotAdultException}. It sets the HTTP status code to 403
	 * (FORBIDDEN) and returns a response with the error message from the exception.
	 *
	 * @param ex The exception to handle
	 * @return A response with the error message and HTTP status code
	 */
	@ExceptionHandler(UserNotAdultException.class)
	public ResponseEntity<ReturnMessageDto> UserNotAdultException(UserNotAdultException ex) {
		ReturnMessageDto error = new ReturnMessageDto();
		error.setHttpstatus(HttpStatus.FORBIDDEN.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

	/**
	 * This method handles the MethodArgumentNotValidException thrown by the
	 * application. It maps the validation errors to a Map object and returns it as
	 * the body of the response.
	 *
	 * @param ex the MethodArgumentNotValidException to be handled
	 * @return a ResponseEntity with a status code of 400 (Bad Request) and a Map
	 * object as the body containing the validation errors
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errors);
	}
}
