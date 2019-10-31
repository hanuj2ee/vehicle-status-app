package com.vsa.rest.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vsa.dao.service.exception.FindException;
import com.vsa.dao.service.exception.InsertException;
import com.vsa.dao.service.exception.InvalidRequestException;
import com.vsa.rest.model.Response;
import com.vsa.rest.util.Constants;

/**
 * {@link VSAExceptionHandler} is the exception handler at application level
 * which extends the functionality of {@link ResponseEntityExceptionHandler}
 *
 * @author Hanumat
 *
 */
@ControllerAdvice
public class VSAExceptionHandler extends ResponseEntityExceptionHandler {
	/**
	 * Logger Instance
	 */
	private final Logger logger = LoggerFactory
			.getLogger(VSAExceptionHandler.class);

	/**
	 * Get the {@link ResponseEntity} instance which has {@link Response} as
	 * type from given message.
	 *
	 * @param message - Error message to sent to UI
	 * @return
	 */
	private ResponseEntity<Response> getResponse(final String message) {
		final Response error = new Response();
		error.setMessage(message);
		error.setType(Constants.ERROR);
		return ResponseEntity.ok(error);
	}

	/**
	 * This is the generic exception handler
	 *
	 * @param ex      - Instance of {@link Exception}
	 * @param request - Instance of {@link WebRequest}
	 * @return Instance of {@link ResponseEntity} which has {@link Response} as
	 *         type
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Response> handleAllExceptions(
			final Exception ex, final WebRequest request) {
		logger.error(ex.getMessage(), ex);
		return getResponse(ex.getMessage());
	}

	/**
	 * This is the FindException handler
	 *
	 * @param ex      - Instance of {@link FindException}
	 * @param request - Instance of {@link WebRequest}
	 * @return Instance of {@link ResponseEntity} which has {@link Response} as
	 *         type
	 */

	@ExceptionHandler(FindException.class)
	public final ResponseEntity<Response> handleFindException(
			final FindException ex, final WebRequest request) {
		logger.error(ex.getMessage(), ex);
		return getResponse(ex.getMessage());
	}

	/**
	 * This is the StorageException handler
	 *
	 * @param ex      - Instance of {@link StorageException}
	 * @param request - Instance of {@link WebRequest}
	 * @return Instance of {@link ResponseEntity} which has {@link Response} as
	 *         type
	 */

	@ExceptionHandler(InsertException.class)
	public final ResponseEntity<Response> handleInsertException(
			final InsertException ex, final WebRequest request) {
		logger.error(ex.getMessage(), ex);
		return getResponse(ex.getMessage());
	}

	/**
	 * This is the InvalidRequestException handler
	 *
	 * @param ex      - Instance of {@link InvalidRequestException}
	 * @param request - Instance of {@link WebRequest}
	 * @return Instance of {@link ResponseEntity} which has {@link Response} as
	 *         type
	 */
	@ExceptionHandler(InvalidRequestException.class)
	public final ResponseEntity<Response> handleInvalidRequestException(
			final InvalidRequestException ex, final WebRequest request) {
		logger.error(ex.getMessage(), ex);
		return getResponse(ex.getMessage());
	}

	/**
	 * This is the MethodArgumentNotValidException handler
	 *
	 * @param ex      - Instance of {@link MethodArgumentNotValidException}
	 * @param request - Instance of {@link WebRequest}
	 * @return Instance of {@link ResponseEntity} which has {@link Response} as
	 *         type
	 */
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(
			final MethodArgumentNotValidException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {
		logger.error(ex.getMessage(), ex);
		final List<String> details = new ArrayList<>();
		for (final ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		final Response error = new Response();
		error.setMessage(String.join("\n", details));
		error.setType(Constants.ERROR);
		return ResponseEntity.ok(error);
	}

}
