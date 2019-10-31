package com.vsa.dao.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception is thrown request is invalid
 * 
 * @author Hanumant
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException {

	/**
	 * Serial Version UID
	 */

	private static final long serialVersionUID = 2690477290854257080L;

	/**
	 * Default constructor
	 */
	public InvalidRequestException() {
		super();
	}

	/**
	 * Constructor used set exception message and cause
	 * 
	 * @param message - Exception Message
	 * @param cause   - Cause of exception
	 */
	public InvalidRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor used set exception message
	 * 
	 * @param message - Exception Message
	 */
	public InvalidRequestException(String message) {
		super(message);
	}

	/**
	 * Constructor used set exception cause
	 * 
	 * @param cause - Cause of exception
	 */
	public InvalidRequestException(Throwable cause) {
		super(cause);
	}

}
