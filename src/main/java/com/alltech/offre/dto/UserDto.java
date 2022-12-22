/**
 * This class represents a data transfer object (DTO) for a user.
 * It is used to transfer user data between the client and server.
 *
 * @author HAMI Meryem
 * @version 1.0
 */
package com.alltech.offre.dto;

import com.alltech.offre.enums.Country;
import com.alltech.offre.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	@NotNull
	@NotBlank(message = "username is required")
	@Schema(description = "The user's username")
	private String userName;

	@NotNull(message = "Birthday is required")
	@Schema(description = "The user's birthday")
	private LocalDate birthday;

	@Schema(description = "The user's number phone")
	private String numberPhone;

	@NotNull(message = "Country is required")
	@Enumerated(EnumType.STRING)
	@Schema(description = "The user's country")
	private Country country;

	@Schema(description = "The user's gender")
	private Gender gender;

}
