package com.anti289.wcd.endpoint;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.anti289.Application;
import com.anti289.base.endpoint.response.ErrorResponse;
import com.anti289.wcd.endpoint.request.FetchCreateRequest;
import com.anti289.wcd.endpoint.response.FetchEmListResponse;
import com.anti289.wcd.endpoint.response.FetchMeResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.restassured.response.Response;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {Application.class})
class FetchRestEndpointTest {

	public static final String USERNAME = "wayne";
	public static final String PATH_FETCH = "fetch";
	public static final String PATH_FETCH_CREATE = PATH_FETCH + "/create";
	@Value("${local.server.port}")
	private int port;


	@Test
	void testCreateNoUser() {
		Response response = callCreateFetchMe(port, null, "");
		ErrorResponse error = response.then().statusCode(HttpStatus.BAD_REQUEST.value()).extract().as(ErrorResponse.class);
		assertEquals("Illegal argument", error.message());
	}

	@Test
	void testCreateFetchMe() throws JsonProcessingException {
		Response response = UserRestEndpointTest.callCreateUser(port, USERNAME);
		response.then().statusCode(HttpStatus.OK.value());

		Response responseCreate = callCreateFetchMe(port, USERNAME, "http://localhost:" + port + "/users");
		FetchMeResponse fetchMeResponse = responseCreate.then().statusCode(HttpStatus.OK.value()).extract().as(FetchMeResponse.class);

		assertNotNull(fetchMeResponse);

		Response responseFM = callGetUserFetchMes(port, USERNAME);
		FetchEmListResponse listResponse = responseFM.then().statusCode(HttpStatus.OK.value()).extract().as(FetchEmListResponse.class);

		assertFalse(listResponse.fetchMes().isEmpty());
	}

	static Response callGetUserFetchMes(int port, String username) {
		return given()
				.port(port)
				.when()
				.get(PATH_FETCH + "/" + username);
	}

	static Response callCreateFetchMe(int port, String username, String fetchUrl) {
		return given()
				.port(port)
				.body(new FetchCreateRequest(username, fetchUrl))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.post(PATH_FETCH_CREATE);
	}
}
