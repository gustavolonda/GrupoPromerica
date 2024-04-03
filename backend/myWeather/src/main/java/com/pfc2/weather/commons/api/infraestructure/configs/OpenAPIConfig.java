package com.pfc2.weather.commons.api.infraestructure.configs;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration

public class OpenAPIConfig {

  @Value("${myweather.openapi.dev-url}")
  private String devUrl;
  private static final String BEARER_AUTH ="Bearer Authentication";

  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Server URL in Development environment");


    Contact contact = new Contact();
    contact.setEmail("gustavolonda@gmail.com");
    contact.setName("Gustavo Londa");
    contact.setUrl("https://www.linkedin.com/in/gustavo-londa/");

    License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    Info info = new Info()
        .title("Prueba API")
        .version("1.0")
        .contact(contact)
        .description("This API exposes endpoints to manage tests.").termsOfService("https://www.linkedin.com/in/gustavo-londa/")
        .license(mitLicense);

    return new OpenAPI().addSecurityItem(new SecurityRequirement().addList(BEARER_AUTH))
		    			.components(new Components().addSecuritySchemes(BEARER_AUTH, createAPIKeyScheme()))
		    			.info(info)
		    			.servers(List.of(devServer));
  }
  private SecurityScheme createAPIKeyScheme() {
	    return new SecurityScheme().type(SecurityScheme.Type.HTTP)
	        .bearerFormat("JWT")
	        .scheme("bearer");
	}
}