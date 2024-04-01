package com.pfc2.weather.openweathermap.domains.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rain { 
	@JsonProperty(value = "1h")
    private double _1h;
}
