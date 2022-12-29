package com.anti289.wcd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anti289.base.exception.ConflictServiceException;
import com.anti289.wcd.repository.UserRepositoryService;
import com.anti289.wcd.dto.User;

import lombok.AllArgsConstructor;


/**
 * Service that gives restricted access to the persisted {@link User users}
 *
 * @author Andreas Timm
 * @since 1.0.0
 */
@Service
@AllArgsConstructor
public class UserService {

	private UserRepositoryService userRepositoryService;

	public List<User> getAllUsers() {
		return userRepositoryService.getUsers();
	}

	public User createUser(String name) throws ConflictServiceException {
		if (userRepositoryService.getUser(name) != null) {
			throw new ConflictServiceException("name not available");
		}
		return userRepositoryService.createUser(name);
	}

}
