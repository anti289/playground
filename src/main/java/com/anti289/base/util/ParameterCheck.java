package com.anti289.base.util;

import com.anti289.base.exception.IllegalArgumentServiceException;
import com.anti289.base.exception.MissingArgumentServiceException;
import com.anti289.base.exception.NotFoundServiceException;
import com.anti289.base.exception.ServiceException;


/**
 * Validation of any parameters, throws {@link ServiceException} in case the check fails
 *
 * @author Andreas Timm
 * @since 1.0.0
 */
public class ParameterCheck {
	private ParameterCheck() {
		throw new UnsupportedOperationException("util class, do not	instantiate");
	}

	/**
	 * Checks the given Object; if it is null an {@link IllegalArgumentServiceException} will be thrown, passing the name as i18n argument
	 *
	 * @param o    the Object to be checked
	 * @param name of the Object to inform the caller
	 * @throws IllegalArgumentServiceException if o is null
	 */
	public static void notNull(Object o, String name) throws ServiceException {
		if (o == null) {
			throw new MissingArgumentServiceException(name);
		}
	}

	/**
	 * Checks the given String; if it is null or blank, an {@link IllegalArgumentServiceException} will be thrown, passing the name as i18n argument
	 *
	 * @param str  the String to be checked
	 * @param name of the String to inform the caller
	 * @throws IllegalArgumentServiceException if o is null
	 */
	public static void notNullOrBlank(String str, String name) throws ServiceException {
		if (str == null || str.isBlank()) {
			throw new MissingArgumentServiceException(name);
		}
	}

	/**
	 * Checks the given Object; if it is null an {@link IllegalArgumentServiceException} will be thrown, passing the name as i18n argument
	 *
	 * @param o    the Object to be checked
	 * @param name of the Object to inform the caller
	 * @throws IllegalArgumentServiceException if o is null
	 */
	public static void notNullNotFound(Object o, String name) throws ServiceException {
		if (o == null) {
			throw new NotFoundServiceException(name);
		}
	}

}
