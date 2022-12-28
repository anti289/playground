package com.anti289.wcd.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;


/**
 * Configuration
 *
 * @author Andreas Timm
 * @since 1.0.0
 */
@Data
@ConfigurationProperties("unused")
public class AConfig {

	/**
	 * URL of the remote service, will be validated
	 */
	private String url;

}
