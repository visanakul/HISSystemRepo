package com.ssa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Servlet Initializer
 * 
 * @author VISHAL
 *
 */
public class ServletInitializer extends SpringBootServletInitializer {
	/**
	 * Logger to log
	 */
	private static final Logger LOGGER=LoggerFactory.getLogger(ServletInitializer.class);
	
	/**
	 * Default constructor
	 */
	public ServletInitializer() {
		super();
		LOGGER.debug("***ServletInitializer***");
	}

	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(SsaWebAppApplication.class);
	}

}
