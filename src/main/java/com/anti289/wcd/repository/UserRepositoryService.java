package com.anti289.wcd.repository;

import java.util.ArrayList;
import java.util.List;

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

}
