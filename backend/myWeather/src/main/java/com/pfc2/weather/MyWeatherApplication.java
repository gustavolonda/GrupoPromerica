package com.pfc2.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.pfc2.weather.weatherhistory.infraestructure.repositories.WeatherHistoryRepository;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


@SpringBootApplication
@ComponentScan(basePackages = { "com.pfc2.weather.*" })
@EnableJpaRepositories(basePackageClasses = WeatherHistoryRepository.class)
@EntityScan(basePackages = { "com.pfc2.weather.*"})
@EnableWebMvc

public class MyWeatherApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyWeatherApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(MyWeatherApplication.class, args);
	}
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}
