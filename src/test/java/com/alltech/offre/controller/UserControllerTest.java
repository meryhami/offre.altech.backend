package com.alltech.offre.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.alltech.offre.dto.UserDto;
import com.alltech.offre.enums.Country;
import com.alltech.offre.exceptions.UserNotFoundException;
import com.alltech.offre.service.serviceimpl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private UserServiceImpl userService;

	@Test
	public void testCreate_success() {
		// Arrange
		UserDto userDto = getUserDto();
		when(userService.create(userDto)).thenReturn(userDto);
		// Act
		ResponseEntity<UserDto> result = userController.create(userDto);
		// Assert
		Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
		Assertions.assertEquals(userDto, result.getBody());
		Mockito.verify(userService).create(userDto);
	}

	@Test
	void testUserDetails() throws UserNotFoundException {
		ResponseEntity<UserDto> userEntity = userController.findByUsername("zack");
		Assertions.assertEquals(200, userEntity.getStatusCodeValue());
	}

	@Test
	void testUsernameNotFound() throws UserNotFoundException {
		when(userService.findByUserName(anyString())).thenThrow(UserNotFoundException.class);

		Assertions.assertThrows(UserNotFoundException.class, () -> userController.findByUsername("nonexistent-user"));
	}

	private UserDto getUserDto() {
		UserDto user = new UserDto();
		user.setUserName("meryem");
		user.setBirthday(LocalDate.of(2000, 12, 5));
		user.setCountry(Country.FRANCE);
		return user;
	}
}
