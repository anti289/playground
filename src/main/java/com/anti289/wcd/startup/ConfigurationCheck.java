package com.anti289.wcd.startup;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.anti289.wcd.config.AConfig;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


/**
 * Startup configuration check, application startup will fail if configuration is not usable
 *
 * @author Andreas Timm
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class ConfigurationCheck implements ApplicationListener<ApplicationReadyEvent> {

	private final AConfig aConfig;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		try {
			new URL(aConfig.getUrl());
		} catch (MalformedURLException e) {
			throw new RuntimeException("URL configuration error for property 'unused.url', not a URL: " + aConfig.getUrl(), e);
		}
	}
}