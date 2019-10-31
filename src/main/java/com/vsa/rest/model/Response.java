package com.vsa.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

/**
 * The class holds the details of the rest api response
 *
 * @author Hanumant
 *
 */

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class Response {
	/**
	 * Message details
	 */
	private String message;
	/**
	 * The result of the sent tin response
	 */
	private Object result;
	/**
	 * Type of the message whether it is success or error
	 */
	private String type;
}
