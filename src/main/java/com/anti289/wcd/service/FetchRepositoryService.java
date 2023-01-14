package com.anti289.wcd.service;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anti289.base.exception.ServiceException;
import com.anti289.base.util.ParameterCheck;
import com.anti289.wcd.dto.FetchMe;
import com.anti289.wcd.dto.User;
import com.anti289.wcd.mapper.FetchMapper;
import com.anti289.wcd.repository.FetchRepository;
import com.anti289.wcd.repository.FetchedInstanceRepository;
import com.anti289.wcd.repository.UserRepository;
import com.anti289.wcd.repository.entity.FetchEntity;
import com.anti289.wcd.repository.entity.FetchedInstanceEntity;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class FetchRepositoryService {

	private final FetchRepository fetchRepository;
	private final FetchedInstanceRepository fetchedInstanceRepository;
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

	public void storeFetchResult(FetchEntity fetchMe, byte[] content) throws ServiceException {
		ParameterCheck.notNull(fetchMe, "fetchMe");
		ParameterCheck.notNull(content, "content");

		Optional<FetchedInstanceEntity> latest = fetchedInstanceRepository.findFirstByFetch_IdOrderByCreatedDateDesc(fetchMe.getId());
		System.out.println(new String(content));
		if (latest.isPresent() && Arrays.equals(content, latest.get().getData())) {
			// only store if something changed!
			System.out.println(new String(latest.get().getData()));
			return;
		}
		FetchedInstanceEntity fie = new FetchedInstanceEntity();
		fie.setData(content);
		fie.setFetch(fetchMe);
		FetchedInstanceEntity save = fetchedInstanceRepository.save(fie);
		System.out.println("stored for " + fetchMe.getUrl() + "\n" + new String(content));
	}
}
