package com.anti289.wcd.endpoint;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping
	public UserListResponse list() {
		List<UserResponse> userResponse = userMapper.map(userService.getAllUsers());
		return new UserListResponse(userResponse);
	}

}
