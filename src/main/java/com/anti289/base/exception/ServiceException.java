package com.anti289.base.exception;

import lombok.Getter;


/**
 * General ServiceException to be extended for specific exceptional states
 *
 * @since 1.0.0
 * @author Andreas Timm
 */
@Getter
public class ServiceException extends Exception {

	/**
	 * Key for i18n, to be used by the client
	 */
	private final String i18nKey;

	/**
	 * Arguments with content for the translated error message
	 */
	private final String[] i18nArguments;

	public ServiceException(String message, String i18nKey, String... i18nArguments) {
		super(message);
		this.i18nKey = i18nKey;
		this.i18nArguments = i18nArguments;
	}

}
