package com.anti289.base.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.anti289.base.util.ParameterCheck;
import com.anti289.base.exception.ServiceException;


class ParameterCheckTest {

	@Test
	void nullTest() {
		try {
			ParameterCheck.notNull(null, "Nullobject");
			fail("not null check failed");
		} catch (ServiceException e) {
			assertEquals("Missing argument", e.getMessage());
			assertEquals("error.missing_argument", e.getI18nKey());
			assertEquals("Nullobject", e.getI18nArguments()[0]);
		}
	}

	@Test
	void notNull() throws ServiceException {
		ParameterCheck.notNull(new Object(), "is not null");
	}

	@Test
	void notNullOrBlank() {
		try {
			ParameterCheck.notNullOrBlank(null, "nullobject");
			fail("not null or blank check failed");
		} catch (ServiceException e) {
			assertEquals("Missing argument", e.getMessage());
			assertEquals("error.missing_argument", e.getI18nKey());
			assertEquals("nullobject", e.getI18nArguments()[0]);
		}
		try {
			ParameterCheck.notNullOrBlank("  	", "blank object");
			fail("not null or blank check failed");
		} catch (ServiceException e) {
			assertEquals("Missing argument", e.getMessage());
			assertEquals("error.missing_argument", e.getI18nKey());
			assertEquals("blank object", e.getI18nArguments()[0]);
		}
	}

	@Test
	void notNullNotFound() {
		try {
			ParameterCheck.notNullNotFound(null, "null object");
			fail("not null not found check failed");
		} catch (ServiceException e) {
			assertEquals("Resource not found", e.getMessage());
			assertEquals("error.not_found", e.getI18nKey());
			assertEquals("null object", e.getI18nArguments()[0]);
		}
	}
}