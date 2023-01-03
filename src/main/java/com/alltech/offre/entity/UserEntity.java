/**
 * This class represents a user entity.
 * It is used to store user data in the database.
 *
 * @author HAMI Meryem
 * @version 1.0
 */
package com.alltech.offre.entity;

import com.alltech.offre.enums.Country;
import com.alltech.offre.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
	@Id
	@Column(length = 20)
	private String userName;

	private LocalDate birthday;

	@Enumerated(EnumType.STRING)
	private Country country;

	private String numberPhone;

	@Enumerated(EnumType.STRING)
	private Gender gender;
}
