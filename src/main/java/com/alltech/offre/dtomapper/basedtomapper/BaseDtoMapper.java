/**
 * This abstract class provides a base implementation for mapping between data transfer objects (DTOs) and entities.
 * It defines methods for converting between DTOs and entities, as well as lists of DTOs and entities.
 *
 * @author HAMI Meryem
 * @version 1.0
 */
package com.alltech.offre.dtomapper.basedtomapper;

import java.util.List;

public abstract class BaseDtoMapper<D, E> {

	/**
	 * Converts an entity to a DTO.
	 *
	 * @param entity The entity to convert
	 * @return The DTO representing the entity
	 */
	public abstract D convertToDTO(E entity);

	/**
	 * Converts a DTO to an entity.
	 *
	 * @param dto The DTO to convert
	 * @return The entity representing the DTO
	 */
	public abstract E convertToEntity(D dto);

	/**
	 * Converts a list of entities to a list of DTOs.
	 *
	 * @param entities The list of entities to convert
	 * @return The list of DTOs representing the entities
	 */
	public abstract List<D> convertToListDTO(List<E> entities);

}
