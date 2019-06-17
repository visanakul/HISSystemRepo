package com.ssa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Main method to start IOC container
 * @author VISHAL
 *
 */
@SpringBootApplication
public class SsaWebAppApplication {
	/**
	 * Logger to log
	 */
	private static final Logger logger=LoggerFactory.getLogger(ServletInitializer.class);
	
	/**
	 * Default constructor
	 */
	public SsaWebAppApplication() {
		logger.debug("***SsaWebAppApplication***");
	}

	/**
	 * Main method
	 * @param args
	 */
	public static void main(final String[] args) {
		SpringApplication.run(SsaWebAppApplication.class, args);
	}

}
