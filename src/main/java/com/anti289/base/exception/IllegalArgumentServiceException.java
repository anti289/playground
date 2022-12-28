package com.anti289.base.exception;

/**
 * Exception for an illegal argument in the request
 *
 * @author Andreas Timm
 * @since 1.0.0
 */
public class IllegalArgumentServiceException extends ServiceException {

	public IllegalArgumentServiceException(String message, String i18nKey, String... arguments) {
		super(message, i18nKey, arguments);
	}

	public IllegalArgumentServiceException(String... arguments) {
		super("Illegal argument", "error.illegal_argument", arguments);
	}
}
