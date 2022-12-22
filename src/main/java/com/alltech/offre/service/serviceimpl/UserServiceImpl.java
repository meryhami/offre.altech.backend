/**
 * UserServiceImpl is a service class that provides various operations related to user.
 * It implements the IUserService interface and overrides its methods.
 *
 * @author HAMI Meryem
 */
package com.alltech.offre.service.serviceimpl;

import com.alltech.offre.aspect.log.Logger;
import com.alltech.offre.dto.UserDto;
import com.alltech.offre.dtomapper.UserDtoMapper;
import com.alltech.offre.entity.UserEntity;
import com.alltech.offre.enums.Country;
import com.alltech.offre.exceptions.UserAlreadyExistException;
import com.alltech.offre.exceptions.UserNotAdultException;
import com.alltech.offre.exceptions.UserNotFoundException;
import com.alltech.offre.exceptions.UserNotFrenchResidentException;
import com.alltech.offre.repository.UserRepository;
import com.alltech.offre.service.iservice.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

	private final UserDtoMapper userDtoMapper;

	private final UserRepository userRepository;

	/**
	 * This method is used to create a new user.
	 *
	 * @param userDto An object of UserDto which is used to create a new user.
	 * @return An object of UserDto which represents the created user.
	 * @throws UserAlreadyExistException      If the given username already exists.
	 * @throws UserNotFrenchResidentException If the user is not a French resident.
	 * @throws DataAccessException            If there is any failure while accessing the data.
	 */
	@Override
	public UserDto create(UserDto userDto) throws UserAlreadyExistException, UserNotFrenchResidentException, DataAccessException {
		if (userRepository.existsByUserName(userDto.getUserName())) {
			throw new UserAlreadyExistException("Username already exists!");
		}
		if (Objects.isNull(userDto.getCountry()) || !Country.FRANCE.equals(userDto.getCountry())) {
			throw new UserNotFrenchResidentException("Only french resident are allowed to create an account!");
		}
		if (Period.between(userDto.getBirthday(), LocalDate.now()).getYears() < 18) {
			throw new UserNotAdultException("Only adult are allowed to create an account!");
		}
		UserEntity userEntity = userDtoMapper.convertToEntity(userDto);
		return userDtoMapper.convertToDTO(userRepository.save(userEntity));
	}

	/**
	 * Find a user by their username.
	 *
	 * @param username the username of the user to find
	 * @return the user with the given username, wrapped in a UserDto
	 * @throws UserNotFoundException if no user with the given username exists
	 */
	@Override
	public UserDto findByUserName(String username) throws UserNotFoundException {
		Optional<UserEntity> userOptional = userRepository.findByUserName(username);
		UserEntity user = userOptional.orElseThrow(() -> new UserNotFoundException("No user " + "Found with username : " + username));
		return userDtoMapper.convertToDTO(user);
	}

	/**
	 * Retrieves a list of all users in the system.
	 *
	 * @return a list of {@link UserDto} objects representing all users in the
	 * system
	 */
	@Logger
	@Override
	public List<UserDto> find() {
		return userDtoMapper.convertToListDTO(userRepository.findAll());
	}
}
