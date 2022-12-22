package com.alltech.offre.service;

import com.alltech.offre.dto.UserDto;
import com.alltech.offre.dtomapper.UserDtoMapper;
import com.alltech.offre.entity.UserEntity;
import com.alltech.offre.enums.Country;
import com.alltech.offre.exceptions.UserAlreadyExistException;
import com.alltech.offre.exceptions.UserNotAdultException;
import com.alltech.offre.exceptions.UserNotFoundException;
import com.alltech.offre.exceptions.UserNotFrenchResidentException;
import com.alltech.offre.repository.UserRepository;
import com.alltech.offre.service.serviceimpl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private UserDtoMapper userDtoMapper;

	private UserDto userDto;

	private UserEntity userEntity;

	@BeforeEach
	void setUp() {
		userDto = getUserDto();
		userEntity = getUserEntity();
	}

	@Test
	public void nominalTest() {
		Mockito.when(userRepository.existsByUserName(userDto.getUserName())).thenReturn(false);
		Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);
		Mockito.when(userDtoMapper.convertToEntity(userDto)).thenReturn(userEntity);
		Mockito.when(userDtoMapper.convertToDTO(userEntity)).thenReturn(userDto);
		// Act
		UserDto result = userService.create(userDto);
		Assertions.assertEquals(userDto, result);
		Mockito.verify(userRepository).existsByUserName(userDto.getUserName());
		Mockito.verify(userRepository).save(userEntity);
		Mockito.verify(userDtoMapper).convertToEntity(userDto);
		Mockito.verify(userDtoMapper).convertToDTO(userEntity);

	}

	@Test
	public void testCreate_throwsUserAlreadyExistException() throws Exception {
		// given
		UserDto userDto = new UserDto();
		userDto.setUserName("test-username");
		Mockito.when(userRepository.existsByUserName("test-username")).thenReturn(true);
		// when
		UserAlreadyExistException exception = Assertions.assertThrows(UserAlreadyExistException.class, () -> {
			userService.create(userDto);
		});

		// then
		Assertions.assertEquals("Username already exists!", exception.getMessage());
	}

	@Test
	public void testCreate_nonAdult_shouldThrowUserNotAdultException() {
		// Given
		UserDto userDto = new UserDto();
		userDto.setUserName("new_username");
		userDto.setCountry(Country.FRANCE);
		userDto.setBirthday(LocalDate.of(2012, 12, 5));
		Mockito.when(userRepository.existsByUserName("new_username")).thenReturn(false);
		// When
		UserNotAdultException exception = Assertions.assertThrows(UserNotAdultException.class, () -> userService.create(userDto));
		Assertions.assertEquals("Only adult are allowed to create an account!", exception.getMessage());
	}

	@Test
	public void testCreate_nonFrenchResident_shouldThrowUserNotFrenshResidentException() {
		// Given
		UserDto userDto = new UserDto();
		userDto.setUserName("new_username");
		userDto.setCountry(Country.USA);
		UserEntity userEntity = new UserEntity();
		userEntity.setUserName(userDto.getUserName());
		userEntity.setCountry(userDto.getCountry());
		Mockito.when(userRepository.existsByUserName("new_username")).thenReturn(false);
		// When
		UserNotFrenchResidentException exception = Assertions.assertThrows(UserNotFrenchResidentException.class, () -> userService.create(userDto));
		Assertions.assertEquals("Only french resident are allowed to create an account!", exception.getMessage());
	}

	@Test
	public void testFindByUserName_success() throws Exception {
		// Arrange
		String username = userDto.getUserName();
		Mockito.when(userRepository.findByUserName(username)).thenReturn(Optional.of(userEntity));
		Mockito.when(userDtoMapper.convertToDTO(userEntity)).thenReturn(userDto);

		// Act
		UserDto result = userService.findByUserName(username);

		// Assert
		Assertions.assertEquals(userDto, result);
		Mockito.verify(userRepository).findByUserName(username);
		Mockito.verify(userDtoMapper).convertToDTO(userEntity);
	}

	@Test
	public void testFindByUserName_userNotFound() throws Exception {
		// Arrange
		String username = userDto.getUserName();
		Mockito.when(userRepository.findByUserName(username)).thenReturn(Optional.empty());

		// Act and assert
		Exception exception = Assertions.assertThrows(UserNotFoundException.class, () -> userService.findByUserName(username));
		Assertions.assertEquals("No user Found with username : meryem", exception.getMessage());

		Mockito.verify(userRepository).findByUserName(username);
		Mockito.verify(userDtoMapper, Mockito.never()).convertToDTO(Mockito.any(UserEntity.class));
	}

	@Test
	public void testFind_success() {
		// Arrange
		UserEntity userEntity1 = userEntity;
		UserEntity userEntity2 = new UserEntity();
		userEntity2.setUserName("test_username2");

		UserDto userDto1 = userDto;

		UserDto userDto2 = new UserDto();
		userDto2.setUserName("test_username2");

		List<UserEntity> userEntityList = Arrays.asList(userEntity1, userEntity2);
		List<UserDto> expectedResult = Arrays.asList(userDto1, userDto2);

		Mockito.when(userRepository.findAll()).thenReturn(userEntityList);
		Mockito.when(userDtoMapper.convertToListDTO(userEntityList)).thenReturn(expectedResult);

		// Act
		List<UserDto> result = userService.find();

		// Assert
		Assertions.assertEquals(expectedResult, result);
		Mockito.verify(userRepository).findAll();
		Mockito.verify(userDtoMapper).convertToListDTO(userEntityList);
	}

	private UserDto getUserDto() {
		UserDto user = new UserDto();
		user.setUserName("meryem");
		user.setBirthday(LocalDate.of(2000, 12, 5));
		user.setCountry(Country.FRANCE);
		return user;
	}

	private UserEntity getUserEntity() {
		UserEntity user = new UserEntity();
		user.setUserName("meryem");
		user.setBirthday(LocalDate.of(2000, 12, 5));
		user.setCountry(Country.FRANCE);
		return user;
	}
}
