package com.pfc2.weather.commons.api.infraestructure.configs;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfc2.weather.commons.api.domains.exception.ErrorResponse;
import com.pfc2.weather.commons.api.domains.exception.ResponseException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Component
public class JwtAuthEntryPoint implements AccessDeniedHandler, AuthenticationEntryPoint {

    @Autowired
    ObjectMapper mapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException, ServletException {
		// Called when the user tries to access an endpoint which requires to be authenticated
        // we just return unauthorizaed
        log.info("Unauthorized error. Message - {}", e.getMessage());

        ServletServerHttpResponse res = new ServletServerHttpResponse(response);
        res.setStatusCode(HttpStatus.UNAUTHORIZED);
        res.getServletResponse().setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        res.getBody().write(mapper.writeValueAsString(new ErrorResponse().builder()
																		.code(String.valueOf(HttpStatus.UNAUTHORIZED.value()))
																		.errors(ResponseException.responseExceptionCreate(HttpStatus.UNAUTHORIZED.name(), e))
																		.build()).getBytes());
		
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException e) throws IOException, ServletException {
		log.info("Unauthorized error. Message - {}", e.getMessage());

        ServletServerHttpResponse res = new ServletServerHttpResponse(response);
        res.setStatusCode(HttpStatus.UNAUTHORIZED);
        res.getServletResponse().setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        res.getBody().write(mapper.writeValueAsString(new ErrorResponse().builder()
														.code(String.valueOf(HttpStatus.UNAUTHORIZED.value()))
														.errors(ResponseException.responseExceptionCreate(HttpStatus.UNAUTHORIZED.name(), e))
														.build()).getBytes());
		
	}
}

