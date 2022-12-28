package com.anti289.base.exception;

/**
 * Resource not found exception
 *
 * @author Andreas Timm
 * @since 1.0.0
 */
public class NotFoundServiceException extends ServiceException {

	public NotFoundServiceException(String message, String i18nKey, String... i18nArguments) {
		super(message, i18nKey, i18nArguments);
	}

	public NotFoundServiceException(String... i18nArguments) {
		this("Resource not found", "error.not_found", i18nArguments);
	}
}
