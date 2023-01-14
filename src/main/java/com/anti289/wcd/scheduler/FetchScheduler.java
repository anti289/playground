package com.anti289.wcd.scheduler;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.logging.Level;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.anti289.base.exception.ServiceException;
import com.anti289.base.util.ParameterCheck;
import com.anti289.wcd.repository.FetchRepository;
import com.anti289.wcd.repository.UserRepository;
import com.anti289.wcd.repository.entity.FetchEntity;
import com.anti289.wcd.repository.entity.UserEntity;
import com.anti289.wcd.service.FetchRepositoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;


@Component
@RequiredArgsConstructor
@Log
public class FetchScheduler {

	private final FetchRepositoryService fetchRepositoryService;
	private final FetchRepository fetchRepository;
	private final UserRepository userRepository;

	/**
	 * load all fetch requests
	 */
	@Scheduled(initialDelay = 1000, fixedDelay = 5000)
	// Using fixedDelay here to decrease load, could use fixedRate to make sure all changes are found in a timely manner
	void fetch() {
		log.info("running fetch scheduler");
		Iterable<UserEntity> users = userRepository.findAll();
		for (UserEntity user : users) {
			List<FetchEntity> byUser = fetchRepository.findByUser(user);
			try {
				doFetch(byUser);
			} catch (ServiceException e) {
				log.log(Level.WARNING, e, () -> "failed to get user fetchMes " + byUser);
			}
		}
	}

	/**
	 * Fetch the entries from the given list and store the latest version in the database
	 *
	 * @param fetchems list of {@link FetchEntity} to be loaded
	 * @throws ServiceException
	 */
	private void doFetch(List<FetchEntity> fetchems) throws ServiceException {
		ParameterCheck.notNull(fetchems, "fetchems");

		// fetch them all!
		HttpClient client = HttpClient.newHttpClient();
		for (FetchEntity fetchMe : fetchems) {
			try {
				HttpRequest request = HttpRequest.newBuilder()
						.uri(URI.create(fetchMe.getUrl()))
						.GET()
						.build();
				HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
				fetchRepositoryService.storeFetchResult(fetchMe, response.body());
			} catch (IOException | InterruptedException | IllegalArgumentException exception) {
				log.log(Level.WARNING, exception, () -> "failed to load " + fetchMe);
			}
		}
	}
}
