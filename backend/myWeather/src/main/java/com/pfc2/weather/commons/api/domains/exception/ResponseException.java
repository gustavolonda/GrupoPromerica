package com.pfc2.weather.commons.api.domains.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseException{
	private String statusCode;
	private String detail;
	
	public static ResponseException responseExceptionCreate(String statusCode, Exception exception) {
		
		return ResponseException.builder()
								.statusCode(statusCode)
								.detail(exception.getMessage())
								.build();		
	}
    
}