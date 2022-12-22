package com.alltech.offre.dtoMapper;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alltech.offre.dto.UserDto;
import com.alltech.offre.dtomapper.UserDtoMapper;
import com.alltech.offre.entity.UserEntity;
import com.alltech.offre.enums.Country;
import com.alltech.offre.enums.Gender;

@ExtendWith(MockitoExtension.class)
class UserDtoMapperTest {
	@InjectMocks
	private UserDtoMapper userDtoMapper;

	@Test
	void dtoToEntity() {
		UserDto userDto = getUserDto();
		UserEntity result = userDtoMapper.convertToEntity(userDto);
		Assertions.assertEquals(userDto.getUserName(), result.getUserName());
		Assertions.assertEquals(userDto.getBirthday(), result.getBirthday());
		Assertions.assertEquals(userDto.getGender(), result.getGender());
		Assertions.assertEquals(userDto.getCountry(), result.getCountry());
		Assertions.assertEquals(userDto.getNumberPhone(), result.getNumberPhone());
	}

	@Test
	void entityToDto() {
		UserEntity userEntity = getUserEntity();
		UserDto result = userDtoMapper.convertToDTO(userEntity);
		Assertions.assertEquals(userEntity.getUserName(), result.getUserName());
		Assertions.assertEquals(userEntity.getBirthday(), result.getBirthday());
		Assertions.assertEquals(userEntity.getGender(), result.getGender());
		Assertions.assertEquals(userEntity.getCountry(), result.getCountry());
		Assertions.assertEquals(userEntity.getNumberPhone(), result.getNumberPhone());
	}

	private UserDto getUserDto() {
		UserDto user = new UserDto();
		user.setUserName("meryem");
		user.setBirthday(LocalDate.of(2000, 12, 5));
		user.setCountry(Country.FRANCE);
		user.setGender(Gender.FEMALE);
		return user;
	}

	private UserEntity getUserEntity() {
		UserEntity user = new UserEntity();
		user.setUserName("meryem");
		user.setBirthday(LocalDate.of(2000, 12, 5));
		user.setCountry(Country.FRANCE);
		user.setGender(Gender.FEMALE);
		return user;
	}
}
