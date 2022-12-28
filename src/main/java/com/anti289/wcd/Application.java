package com.anti289.wcd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.anti289.wcd.config.AConfig;


/**
 * Main application class for Spring Boot
 *
 * @author Andreas Timm
 * @since 1.0.0
 */
@SpringBootApplication
@EnableConfigurationProperties(AConfig.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
