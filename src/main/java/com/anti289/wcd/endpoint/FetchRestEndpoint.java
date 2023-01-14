package com.anti289.wcd.endpoint;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anti289.base.exception.IllegalArgumentServiceException;
import com.anti289.base.exception.ServiceException;
import com.anti289.base.util.ParameterCheck;
import com.anti289.wcd.endpoint.request.FetchCreateRequest;
import com.anti289.wcd.endpoint.response.FetchEmListResponse;
import com.anti289.wcd.endpoint.response.FetchMeResponse;
import com.anti289.wcd.mapper.FetchMapper;
import com.anti289.wcd.service.FetchService;

import lombok.RequiredArgsConstructor;


/**
 * REST endpoint to manage user fetch configurations in this service
 *
 * @author Andreas Timm
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "fetch")
@RequiredArgsConstructor
public class FetchRestEndpoint {

	private final FetchService fetchService;

	private final FetchMapper fetchMapper;

	/**
	 * Create a fetch request for a user, implicit GET and regularly scheduled fetch by global timer
	 *
	 * @param request with data for the creation
	 * @return the stored information as a {@link FetchMeResponse}
	 * @throws ServiceException
	 */
	@PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public FetchMeResponse create(@RequestBody FetchCreateRequest request) throws ServiceException {
		ParameterCheck.notNull(request, "request");

		try {
			URL url = new URL(request.url());
			return fetchMapper.mapResponse(fetchService.create(request.name(), url));
		} catch (MalformedURLException e) {
			throw new IllegalArgumentServiceException("provided URL is malformed: " + request.url());
		}
	}

	/**
	 * Create a fetch request for a user, implicit GET and regularly scheduled fetch by global timer
	 *
	 * @param username
	 * @throws ServiceException
	 */
	@GetMapping("{username}")
	public FetchEmListResponse get(@PathVariable("username") String username) throws ServiceException {
		ParameterCheck.notNull(username, "username");

		return new FetchEmListResponse(username, fetchMapper.mapResponse(fetchService.getAll(username)));
	}
}
