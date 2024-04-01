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
public class Main {
	private double temp;
	@JsonProperty(value = "feels_like")
    private double feelsLike;
	@JsonProperty(value = "temp_min")
    private double tempMin;
	@JsonProperty(value = "temp_max")
    private double tempMax;
    private int pressure;
    private int humidity;
    @JsonProperty(value = "sea_level")
    private int seaLevel;
    @JsonProperty(value = "grnd_level")
    private int grndLevel;
}
