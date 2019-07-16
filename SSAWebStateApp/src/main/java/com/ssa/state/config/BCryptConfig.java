package com.ssa.state.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BCryptConfig {
	/**
	 * SLF4J Logger
	 */
	private static final Logger LOGGER=LoggerFactory.getLogger(BCryptConfig.class);

	/**
	 * Default Constructor
	 */
	public BCryptConfig() {
		LOGGER.info("***BCryptConfig***");
	}
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		LOGGER.info("***Creating Bean of BCryptPasswordEncoder***");
		return new BCryptPasswordEncoder();
	}
}
