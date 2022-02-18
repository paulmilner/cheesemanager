package com.jeannot.cheesemanager;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Application {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	@Autowired
	private Environment env;
	
	public static void main(String[] args) {

		SpringApplication.run(Application.class);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		LOGGER.info("Setting up CORS");
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				
				if (env.getProperty("cors.required").equals("true")) {

					LOGGER.info("Setting up CORS for servers=" + env.getProperty("cors.allowed.server"));

					List<String> endPoints = new ArrayList<>();
					endPoints.add("/api/**");				

					for (String mapping : endPoints) {
						registry.addMapping(mapping)
								.allowedOrigins(env.getProperty("cors.allowed.server").split(","))
								.allowedMethods("PUT", "GET", "POST", "DELETE");
					}
				}

			}
		};
	}
}
