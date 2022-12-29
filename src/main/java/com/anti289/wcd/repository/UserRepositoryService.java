package com.anti289.wcd.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.anti289.wcd.dto.User;
import com.anti289.wcd.mapper.UserMapper;
import com.anti289.wcd.repository.entity.UserEntity;

import lombok.AllArgsConstructor;


/**
 * Access to the {@link UserRepository} is managed by this service to keep Entity objects unchanged.
 *
 * @author Andreas Timm
 * @since 1.0.0
 */
@Service
@AllArgsConstructor
public class UserRepositoryService {

	private UserRepository userRepository;

	private UserMapper userMapper;

	/**
	 * Find all users that registered here, ever
	 *
	 * @return List of all {@link User Users}
	 */
	public List<User> getUsers() {
		Iterable<UserEntity> users = userRepository.findAll();
		List<User> result = new ArrayList<>();
		for (UserEntity str : users) {
			result.add(userMapper.map(str));
		}
		return result;
	}

	/**
	 * Load a specific user
	 *
	 * @param username of the user to be loaded
	 * @return the {@link User} or {@code null}
	 */
	public User getUser(String username) {
		return userMapper.map(userRepository.findByName(username));
	}

	/**
	 * Create a new user with the given name
	 *
	 * @param name of the new user
	 * @return the {@link User} dto
	 */
	public User createUser(String name) {
		UserEntity user = new UserEntity();
		user.setName(name);
		user.setExternalId(UUID.randomUUID().toString());
		return userMapper.map(userRepository.save(user));
	}

	/**
	 * Delete an existing user identified by name and external ID
	 *
	 * @param name       of the user to be deleted
	 * @param externalId of the user to be deleted
	 */
	public void deleteUser(String name, String externalId) {
		UserEntity user = userRepository.findByNameAndExternalId(name, externalId);
		if (user != null) {
			userRepository.delete(user);
		}
	}

}
