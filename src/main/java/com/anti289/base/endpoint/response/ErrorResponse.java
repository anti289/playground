package com.anti289.base.endpoint.response;

import com.anti289.base.exception.ServiceException;


/**
 * Response object containing a default message and an i18n key for translation
 *
 * @author Andreas Timm
 * @since 1.0.0
 */
public record ErrorResponse(String message, String i18nKey, String[] i18nArguments) {
	public ErrorResponse(ServiceException se) {
		this(se.getMessage(), se.getI18nKey(), se.getI18nArguments());
	}
}
