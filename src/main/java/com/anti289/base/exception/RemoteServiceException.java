package com.anti289.base.exception;


/**
 * Exception to signal that the remote call failed.
 *
 * @author Andreas Timm
 * @since 1.0.0
 */
public class RemoteServiceException extends ServiceException {

	public RemoteServiceException(String message, String i18nKey, String... i18nArguments) {
		super(message, i18nKey, i18nArguments);
	}

	public RemoteServiceException(String... i18nArguments) {
		this("remote service invocation failed", "error.remote_service_failed", i18nArguments);
	}

}
