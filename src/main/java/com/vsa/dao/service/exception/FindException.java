package com.vsa.dao.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception is thrown if entity is not found
 * 
 * @author Hanumant
 *
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FindException extends RuntimeException {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -6342048667196934527L;

	/**
	 * Default constructor
	 */
	public FindException() {
		super();
	}

	/**
	 * Constructor used set exception message and cause
	 * 
	 * @param message - Exception Message
	 * @param cause   - Cause of exception
	 */
	public FindException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor used set exception message
	 * 
	 * @param message - Exception Message
	 */
	public FindException(String message) {
		super(message);
	}

	/**
	 * Constructor used set exception cause
	 * 
	 * @param cause - Cause of exception
	 */
	public FindException(Throwable cause) {
		super(cause);
	}

}
