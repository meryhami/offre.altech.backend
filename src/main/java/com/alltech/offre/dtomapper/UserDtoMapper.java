/**
 * This class provides an implementation for mapping between UserDto objects and UserEntity objects.
 * It extends the BaseDtoMapper class and provides specific implementations for the abstract methods defined there.
 *
 * @author HAMI Meryem
 * @version 1.0
 */
package com.alltech.offre.dtomapper;

import com.alltech.offre.dto.UserDto;
import com.alltech.offre.dtomapper.basedtomapper.BaseDtoMapper;
import com.alltech.offre.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoMapper extends BaseDtoMapper<UserDto, UserEntity> {

	/**
	 * Converts a UserEntity object to a UserDto object.
	 *
	 * @param entity The UserEntity object to convert
	 * @return The UserDto object representing the UserEntity
	 */
	@Override
	public UserDto convertToDTO(UserEntity entity) {
		final UserDto userDto = new UserDto();
		userDto.setUserName(entity.getUserName());
		userDto.setCountry(entity.getCountry());
		userDto.setNumberPhone(entity.getNumberPhone());
		userDto.setGender(entity.getGender());
		userDto.setBirthday(entity.getBirthday());
		return userDto;
	}

	/**
	 * Converts a UserDto object to a UserEntity object.
	 *
	 * @param dto The UserDto object to convert
	 * @return The UserEntity object representing the UserDto
	 */
	@Override
	public UserEntity convertToEntity(UserDto dto) {
		final UserEntity userEntity = new UserEntity();
		userEntity.setUserName(dto.getUserName());
		userEntity.setCountry(dto.getCountry());
		userEntity.setNumberPhone(dto.getNumberPhone());
		userEntity.setGender(dto.getGender());
		userEntity.setBirthday(dto.getBirthday());
		return userEntity;
	}

	/**
	 * Converts a list of UserEntity instances to a list of UserDto instances.
	 *
	 * @param entities The list of UserEntity instances to convert
	 * @return The list of UserDto instances representing the UserEntity instances
	 */
	@Override
	public List<UserDto> convertToListDTO(List<UserEntity> entities) {
		return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

}
