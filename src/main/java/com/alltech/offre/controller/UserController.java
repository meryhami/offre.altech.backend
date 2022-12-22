package com.alltech.offre.controller;

import com.alltech.offre.aspect.log.Logger;
import com.alltech.offre.dto.UserDto;
import com.alltech.offre.exceptions.UserAlreadyExistException;
import com.alltech.offre.exceptions.UserNotFoundException;
import com.alltech.offre.service.iservice.IUserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
@OpenAPIDefinition(info = @Info(title = "User API", version = "1.0"))
public class UserController {
	private final IUserService userService;

	/**
	 * Creates a new user with the information provided in the request body.
	 *
	 * @param userDto a DTO (Data Transfer Object) containing the user information
	 * @return a response entity with the newly created user and an HTTP status of
	 * 201 (Created)
	 * @throws UserAlreadyExistException if a user with the same username already exists
	 */
	@PostMapping
	@Logger
	@Operation(summary = "Create User")
	public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto) throws UserAlreadyExistException {
		return new ResponseEntity<>(userService.create(userDto), HttpStatus.CREATED);
	}

	/**
	 * Returns a list of all users.
	 *
	 * @return a response entity with a list of all users and an HTTP status of 200
	 * (OK)
	 */
	@GetMapping
	@Logger
	@Operation(summary = "Get all users")
	public ResponseEntity<List<UserDto>> find() {
		return ResponseEntity.ok(userService.find());
	}

	/**
	 * Returns the user with the given username.
	 *
	 * @param username the username of the user to retrieve
	 * @return a response entity with the user and an HTTP status of 200 (OK)
	 * @throws UserNotFoundException if no user with the given username exists
	 */
	@GetMapping("{username}")
	@Logger
	@Operation(summary = "Get user by username")
	public ResponseEntity<UserDto> findByUsername(@PathVariable(name = "username") String username) throws UserNotFoundException {
		return new ResponseEntity<>(userService.findByUserName(username), HttpStatus.OK);
	}
}
