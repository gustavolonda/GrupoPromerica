package com.pfc2.weather.commons.api.domains.data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBase<T> {
	private String status  = "";
	private String message = "";
	private T result       = (T) "";

}
