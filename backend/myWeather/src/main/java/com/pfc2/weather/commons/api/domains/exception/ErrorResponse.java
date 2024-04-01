
package com.pfc2.weather.commons.api.domains.exception;

import org.springframework.http.ProblemDetail;

import com.pfc2.weather.commons.api.domains.data.ResponseBase;

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
public class ErrorResponse {
	private String code ;
	private Object errors ;

}