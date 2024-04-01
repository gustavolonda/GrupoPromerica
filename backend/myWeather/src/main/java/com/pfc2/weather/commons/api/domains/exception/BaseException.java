package com.pfc2.weather.commons.api.domains.exception;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends Exception {
	private String message;
	private String module;
	private Exception exception;
}