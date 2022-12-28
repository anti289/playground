package com.anti289.base.endpoint;

import java.util.logging.Level;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.anti289.base.endpoint.response.ErrorResponse;
import com.anti289.base.exception.RemoteServiceException;
import com.anti289.base.exception.IllegalArgumentServiceException;
import com.anti289.base.exception.MissingArgumentServiceException;
import com.anti289.base.exception.NotFoundServiceException;
import com.anti289.base.exception.ServiceException;
import com.anti289.base.exception.ConflictServiceException;

import lombok.extern.java.Log;


/**
 * Exception handler to take care of a proper response for any exception during a request
 *
 * @author Andreas Timm
 * @since 1.0.0
 */
@RestControllerAdvice
@Log
public class RestExceptionHandler {

	@ExceptionHandler(value = {IllegalArgumentServiceException.class, MissingArgumentServiceException.class, RemoteServiceException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse badRequestErrorHandler(ServiceException e) {
		log.log(Level.INFO, "Bad request returned", e);

		return new ErrorResponse(e);
	}

	@ExceptionHandler(value = NotFoundServiceException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse notFoundErrorHandler(ServiceException e) {
		log.log(Level.INFO, "Not found returned", e);

		return new ErrorResponse(e);
	}

	@ExceptionHandler(value = ConflictServiceException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse conflictErrorHandler(ServiceException e) {
		log.log(Level.INFO, "Conflict returned", e);

		return new ErrorResponse(e);
	}

	@ExceptionHandler(value = Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse uncaught(Throwable t) {
		log.log(Level.SEVERE, "Internal Server error", t);

		return new ErrorResponse("Service failed, please contact support: " + t.getMessage(),
				"error.internal_server_error", new String[]{t.getMessage()});
	}

}

