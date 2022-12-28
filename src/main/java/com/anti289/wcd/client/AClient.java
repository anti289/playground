package com.anti289.wcd.client;

import org.springframework.stereotype.Component;

import com.anti289.base.client.BaseClient;
import com.anti289.wcd.config.AConfig;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;


/**
 * Client impl, unused
 *
 * @author Andreas Timm
 * @since 1.0.0
 */
@Component
@AllArgsConstructor
@Log
public class AClient extends BaseClient {

	private AConfig aConfig;


	@Override
	protected String getCredentials() {
		return null;
		//Credentials.basic(aConfig.getUser(), aConfig.getPass());
	}

	@Override
	protected String getBaseUrl() {
		return aConfig.getUrl();
	}
}
