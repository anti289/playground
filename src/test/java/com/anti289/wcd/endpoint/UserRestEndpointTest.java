package com.anti289.wcd.endpoint;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.anti289.wcd.Application;
import com.anti289.wcd.endpoint.response.UserListResponse;

import io.restassured.response.Response;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {Application.class})
public class UserRestEndpointTest {

	@Value("${local.server.port}")
	private int port;

	// TODO add OpenApi validation
	//	private final OpenApiValidationFilter validationFilter = new OpenApiValidationFilter("/static/api.yaml");

	@Test
	void testGetAll() {
		UserListResponse actualDTO =
				callGetAllUsers(port)
						.then()
						.statusCode(HttpStatus.OK.value())
						.extract()
						.as(UserListResponse.class);
		assertTrue(actualDTO.getUsers().size() == 0);
	}

	/**
	 * Get all users request
	 *
	 * @param port of the running application
	 * @return the Response object to work with it
	 */
	static Response callGetAllUsers(int port) {
		return given()
				.port(port)
//						.filter(validationFilter)
				.when()
				.get("/users");
	}
}
