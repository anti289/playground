package com.anti289.base.client;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;

import org.springframework.http.HttpStatus;

import com.anti289.base.exception.RemoteServiceException;
import com.anti289.base.exception.ConflictServiceException;
import com.anti289.base.exception.NotFoundServiceException;
import com.anti289.base.exception.ServiceException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.java.Log;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * Re-usable base client implementation, to be implemented by REST clients.
 *
 * @author Andreas Timm
 * @since 1.0.0
 */
@Log
public abstract class BaseClient {

	public static final String FWD_SLASH = "/";

	/**
	 * This client will be used to access the remote, using basic authentication
	 */
	protected OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
		Request request = chain.request();
		String credentials = getCredentials();
		if (credentials != null) {
			request = request.newBuilder().addHeader("Authorization", credentials).build();
		}
		return chain.proceed(request);
	}).build();

	protected final ObjectMapper mapper = new ObjectMapper();

	public BaseClient() {
		// map JSON number to BigDecimal to avoid issues during calculation
		mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
	}

	/**
	 * Prepare credentials for basic auth in client impl, could use {@link okhttp3.Credentials#basic(String, String)}; if this returns {@code null}, no Authorization header will be set
	 *
	 * @return String for Authorization header
	 */
	protected abstract String getCredentials();

	/**
	 * @return the base URL to use when accessing the remote service, will be used when {{@link #resolve(String...)}} resolving a path
	 */
	protected abstract String getBaseUrl();

	/**
	 * GET request
	 *
	 * @param url           resolved full path to the resource
	 * @param responseClazz class of the response
	 * @param <T>           generic response type
	 * @return the response mapped to the given responseClazz
	 * @throws ServiceException with additional information, if the execution failed
	 */
	protected <T> T get(String url, Class<T> responseClazz) throws ServiceException {
		Request request = new Request.Builder()
				.url(url)
				.build();

		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful()) {
				ResponseBody responseBody = response.body();
				if (responseBody == null) {
					throw new RemoteServiceException("body is null");
				}
				return mapper.readValue(responseBody.string(), responseClazz);
			} else {
				throw handleFailedCall(response);
			}
		} catch (IOException ioException) {
			throw handleIOException(ioException);
		}
	}

	/**
	 * POST request
	 *
	 * @param url           resolved full path to the resource
	 * @param json          valid JSON to be sent during the post request
	 * @param responseClazz class of the response
	 * @param <T>           generic response type
	 * @return the response mapped to the given responseClazz
	 * @throws ServiceException with additional information, if the execution failed
	 */
	protected <T> T post(String url, String json, Class<T> responseClazz) throws ServiceException {
		return post(url, json, responseClazz, null, null);
	}

	/**
	 * POST request
	 *
	 * @param url           resolved full path to the resource
	 * @param json          valid JSON to be sent during the post request
	 * @param responseClazz class of the response
	 * @param headerToStore optional name of the response header that should be stored
	 * @param headers       required for headerToStore; the header that should be stored will be stored in this Map with the given key from headerToStore
	 * @param <T>           generic response type
	 * @return the response mapped to the given responseClazz
	 * @throws ServiceException with additional information, if the execution failed
	 */
	protected <T> T post(String url, String json, Class<T> responseClazz, String headerToStore, Map<String, String> headers) throws ServiceException {
		RequestBody body = RequestBody.create(json == null ? "" : json, MediaType.get("application/json; charset=utf-8"));

		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.build();
		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful()) {
				if (headerToStore != null && headers != null) {
					headers.put(headerToStore, response.header(headerToStore));
				}
				ResponseBody responseBody = response.body();
				if (body == null) {
					throw new RemoteServiceException("body is null");
				}
				return mapper.readValue(responseBody.string(), responseClazz);
			} else {
				throw handleFailedCall(response);
			}
		} catch (IOException ioException) {
			throw handleIOException(ioException);
		}
	}

	/**
	 * Handle failed request to respond with a proper http error status
	 *
	 * @param response that we received, that will be consumed here
	 * @return a specific type of a {@link ServiceException}
	 */
	protected ServiceException handleFailedCall(Response response) {
		if (response.code() == HttpStatus.NOT_FOUND.value()) {
			return new NotFoundServiceException(response.toString());
		} else if (response.code() == HttpStatus.CONFLICT.value()) {
			return new ConflictServiceException(response.toString());
		} else {
			return new RemoteServiceException(response.toString());
		}
	}

	/**
	 * General handler for IOExceptions, logs and re-wraps in {@link ServiceException}
	 *
	 * @param ioException that was caught and needs to be mapped to a ServiceException
	 * @return the {@link ServiceException}
	 */
	protected ServiceException handleIOException(IOException ioException) {
		log.log(Level.INFO, ioException.getMessage(), ioException);

		return new ServiceException("call failed", "error.call_failed");
	}

	/**
	 * Resolver to access the given path on the configured server
	 *
	 * @param path to be accessed
	 * @return URL style path
	 */
	protected String resolve(String... path) {
		String url = getBaseUrl();
		return url.endsWith(FWD_SLASH) ? url + String.join(FWD_SLASH, path) : url + FWD_SLASH + String.join(FWD_SLASH, path);
	}

}
