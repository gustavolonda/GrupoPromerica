package com.pfc2.weather.weatherhistory.domains.data;

import static com.pfc2.weather.commons.api.domains.data.UtilMyWeather.FORMAT_DATE;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherHistoryDto {
	private String id;
	private double lat;
	private double lon;
	private String  weather;
	private double tempMin;
	private double tempMax;
	private int humidity;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT_DATE)
	private Date queryServerDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT_DATE)
	private Date createDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FORMAT_DATE)
	private Date modifyDate;
	private String status;
}
