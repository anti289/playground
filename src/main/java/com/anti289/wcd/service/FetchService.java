package com.anti289.wcd.service;

import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Service;

import com.anti289.base.exception.ServiceException;
import com.anti289.base.util.ParameterCheck;
import com.anti289.wcd.dto.FetchMe;
import com.anti289.wcd.dto.User;

import lombok.RequiredArgsConstructor;


/**
 * Service to manage fetch configurations for a user
 *
 * @author Andreas Timm
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class FetchService {

	private final UserRepositoryService userRepositoryService;

	private final FetchRepositoryService fetchRepositoryService;

	/**
	 * Create a new {@link FetchMe} for a user and url
	 *
	 * @param username of the user to create a {@link FetchMe} for
	 * @param url      to be fetched regularly
	 * @return the created mapped {@link FetchMe}
	 * @throws ServiceException in case parameters are incorrect
	 */
	public FetchMe create(String username, URL url) throws ServiceException {
		ParameterCheck.notNullNotFound(username, "username");
		ParameterCheck.notNullOrBlank(url, "url");

		User user = userRepositoryService.getUser(username);
		ParameterCheck.notNullNotFound(user, "user");

		FetchMe fetchme = fetchRepositoryService.findFetchme(user, url);
		if (fetchme != null) {
			return fetchme;
		}

		return fetchRepositoryService.createFetchme(user, url);
	}

	/**
	 * Get all existing fetch requests that were created by the user
	 *
	 * @param username of the user that has some fetch request
	 * @return List of {@link FetchMe}
	 * @throws ServiceException in case the user does not exist
	 */
	public List<FetchMe> getAll(String username) throws ServiceException {
		ParameterCheck.notNullNotFound(username, "username");

		User user = userRepositoryService.getUser(username);
		ParameterCheck.notNullNotFound(user, "user");

		return fetchRepositoryService.findByUser(user);
	}
}
