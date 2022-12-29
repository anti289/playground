package com.anti289.wcd.endpoint;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anti289.base.exception.ServiceException;
import com.anti289.base.util.ParameterCheck;
import com.anti289.wcd.endpoint.request.UserCreateRequest;
import com.anti289.wcd.endpoint.response.UserListResponse;
import com.anti289.wcd.endpoint.response.UserResponse;
import com.anti289.wcd.mapper.UserMapper;
import com.anti289.wcd.service.UserService;

import lombok.AllArgsConstructor;


/**
 * REST endpoint to manage users in this service
 *
 * @author Andreas Timm
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "users")
@AllArgsConstructor
public class UserRestEndpoint {

	private UserService userService;
	private UserMapper userMapper;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public UserListResponse list() {
		List<UserResponse> userResponse = userMapper.map(userService.getAllUsers());
		return new UserListResponse(userResponse);
	}

	@PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserResponse createUser(@RequestBody UserCreateRequest createRequest) throws ServiceException {
		ParameterCheck.notNull(createRequest, "create request");

		return userMapper.map(userService.createUser(createRequest.name()));
	}

}
