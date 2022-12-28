package com.anti289.base.endpoint.response;

import com.anti289.base.exception.ServiceException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Response object containing a default message and an i18n key for translation
 *
 * @since 1.0.0
 * @author Andreas Timm
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

	private String message;
	private String i18nKey;
	private String[] arguments;

	public ErrorResponse(ServiceException e) {
		this.message = e.getMessage();
		this.i18nKey = e.getI18nKey();
		this.arguments = e.getI18nArguments();
	}

}
