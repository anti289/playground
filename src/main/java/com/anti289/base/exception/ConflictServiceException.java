package com.anti289.base.exception;

import com.anti289.base.exception.ServiceException;


/**
 * Conflict found, tell the caller
 *
 * @author Andreas Timm
 * @since 1.0.0
 */
public class ConflictServiceException extends ServiceException {

	public ConflictServiceException(String message, String i18nKey, String... i18nArguments) {
		super(message, i18nKey, i18nArguments);
	}

	public ConflictServiceException(String... i18nArguments) {
		this("Resource conflict, was probably changed since last request", "error.conflict", i18nArguments);
	}

}
