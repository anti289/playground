package com.anti289.wcd.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.anti289.base.exception.ConflictServiceException;
import com.anti289.wcd.dto.User;
import com.anti289.wcd.mapper.UserMapper;
import com.anti289.wcd.repository.UserRepository;
import com.anti289.wcd.repository.entity.UserEntity;

import lombok.RequiredArgsConstructor;


/**
 * Service that gives restricted access to the persisted {@link User users}
 *
 * @author Andreas Timm
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepositoryService userRepositoryService;
	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public List<User> getAllUsers() {
		return userRepositoryService.getUsers();
	}

	public User createUser(String name) throws ConflictServiceException {
		if (userRepository.findByName(name) != null) {
			throw new ConflictServiceException("name not available");
		}
		return createUserInternal(name);
	}

	/**
	 * Create a new user with the given name
	 *
	 * @param name of the new user
	 * @return the {@link User} dto
	 */
	private User createUserInternal(String name) {
		UserEntity user = new UserEntity();
		user.setName(name);
		user.setExternalId(UUID.randomUUID().toString());
		return userMapper.map(userRepository.save(user));
	}

}
