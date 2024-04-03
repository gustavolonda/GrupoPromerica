package com.pfc2.weather.commons.api.infraestructure.configs;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.pfc2.weather.commons.api.domains.exception.ErrorResponse;
import com.pfc2.weather.commons.api.domains.exception.ResponseException;




@RestControllerAdvice
public class RestExceptionHandler  {
	@ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponse().builder()
										.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
										.errors(ResponseException.responseExceptionCreate(HttpStatus.INTERNAL_SERVER_ERROR.name(), e))
										.build());
    }
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleException(HttpMessageNotReadableException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body(new ErrorResponse().builder()
										.code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
										.errors(ResponseException.responseExceptionCreate(HttpStatus.BAD_REQUEST.name(), e))
										.build());
    }
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<?> handleException2(NoHandlerFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body(new ErrorResponse().builder()
													.code(String.valueOf(HttpStatus.NOT_FOUND.value()))
													.errors(ResponseException.responseExceptionCreate(HttpStatus.NOT_FOUND.name(), e))
													.build());
    }
}
