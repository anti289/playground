package com.anti289.base.exception;

/**
 * Exception for insufficient information during request, argument missing
 *
 * @author Andreas Timm
 * @since 1.0.0
 */
public class MissingArgumentServiceException extends ServiceException {

	public MissingArgumentServiceException(String message, String i18nKey, String... arguments) {
		super(message, i18nKey, arguments);
	}

	public MissingArgumentServiceException(String... arguments) {
		this("Missing argument", "error.missing_argument", arguments);
	}
}
