package com.anti289.wcd.endpoint;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.anti289.Application;
import com.anti289.wcd.endpoint.request.UserCreateRequest;
import com.anti289.wcd.endpoint.response.UserListResponse;
import com.anti289.wcd.endpoint.response.UserResponse;
import com.anti289.wcd.service.UserRepositoryService;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.restassured.response.Response;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {Application.class, UserRepositoryService.class})
class UserRestEndpointTest {

	@Value("${local.server.port}")
	private int port;

	@Autowired
	private UserRepositoryService userRepositoryService;

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
		assertEquals(0, actualDTO.users().size());
	}

	@Test
	void testCreate() throws JsonProcessingException {
		String name = "test name";
		UserResponse actualDTO =
				callCreateUser(port, name)
						.then()
						.statusCode(HttpStatus.OK.value())
						.extract()
						.as(UserResponse.class);
		assertEquals(name, actualDTO.name());
		assertNotNull(actualDTO.externalId());

		UserListResponse list =
				callGetAllUsers(port)
						.then()
						.statusCode(HttpStatus.OK.value())
						.extract()
						.as(UserListResponse.class);
		assertEquals(1, list.users().size());
		assertEquals(actualDTO, list.users().get(0));

		userRepositoryService.deleteUser(actualDTO.name(), actualDTO.externalId());
	}

	static Response callCreateUser(int port, String name) throws JsonProcessingException {
		return given()
				.port(port)
//						.filter(validationFilter)
				.when()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(new UserCreateRequest(name))
				.post("users/create");
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
