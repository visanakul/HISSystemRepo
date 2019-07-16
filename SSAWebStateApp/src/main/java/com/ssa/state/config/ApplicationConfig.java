package com.ssa.state.config;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "myapp")
@Data
public class ApplicationConfig {
	/**
	 * Slf4 Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);

	/**
	 * Default constructor
	 */
	public ApplicationConfig() {
		LOGGER.info("***ApplicationConfig***");
	}

	private Map<String, String> data = new HashMap<>();

}
