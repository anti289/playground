package com.anti289.wcd.repository;

import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Service;

import com.anti289.base.exception.ServiceException;
import com.anti289.base.util.ParameterCheck;
import com.anti289.wcd.dto.FetchMe;
import com.anti289.wcd.dto.User;
import com.anti289.wcd.mapper.FetchMapper;
import com.anti289.wcd.repository.entity.FetchEntity;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class FetchRepositoryService {

	private final FetchRepository fetchRepository;
	private final UserRepository userRepository;

	private final FetchMapper fetchMapper;

	public FetchMe findFetchme(User user, URL url) {
		return fetchMapper.map(fetchRepository.findByUserAndUrl(userRepository.findByName(user.name()), url.toExternalForm()));
	}

	public FetchMe createFetchme(User user, URL url) throws ServiceException {
		ParameterCheck.notNullNotFound(user, "user");
		ParameterCheck.notNull(url, "url");

		FetchEntity entity = new FetchEntity();
		entity.setUrl(url.toExternalForm());
		entity.setUser(userRepository.findByName(user.name()));
		entity = fetchRepository.save(entity);
		return fetchMapper.map(entity);
	}

	public List<FetchMe> findByUser(User user) {
		return fetchMapper.map(fetchRepository.findByUser(userRepository.findByName(user.name())));
	}
}
