package com.anti289;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.anti289.wcd.config.AConfig;


/**
 * Main application class for Spring Boot
 *
 * @author Andreas Timm
 * @since 1.0.0
 */
@SpringBootApplication
@EnableConfigurationProperties(AConfig.class)
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
