/**
 * This class represents a data transfer object (DTO) for a return message.
 * It is used to transfer information about the status and message of a request between the client and server.
 *
 * @author HAMI Meryem
 * @version 1.0
 */
package com.alltech.offre.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnMessageDto {
	int httpstatus;

	String message;
}
