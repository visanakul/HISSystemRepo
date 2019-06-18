package com.ssa.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ssa.ServletInitializer;
import com.ssa.util.ConstantUtils;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * Configuration of Swagger
 * @author VISHAL
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	/**
	 * Logger to log
	 */
	private static final Logger LOGGER=LoggerFactory.getLogger(ServletInitializer.class);

	/**
	 * Default constructor
	 */
	public SwaggerConfiguration() {
		LOGGER.debug("***SwaggerConfiguration***");
	}
	
	/**
	 * This method generate documentation
	 * @return
	 */
	@Bean
	public Docket getUserAPIDoc() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(ConstantUtils.RESOURCE_PACKAGE)).build();
	}
}