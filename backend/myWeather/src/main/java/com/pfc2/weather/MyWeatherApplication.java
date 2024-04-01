package com.pfc2.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.pfc2.weather.weatherhistory.infraestructure.repositories.WeatherHistoryRepository;


@SpringBootApplication
@ComponentScan(basePackages = { "com.pfc2.weather.*" })
@EnableJpaRepositories(basePackageClasses = WeatherHistoryRepository.class)
@EntityScan(basePackages = { "com.pfc2.weather.*"})
@EnableWebMvc

public class MyWeatherApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyWeatherApplication.class, args);
	}

}
