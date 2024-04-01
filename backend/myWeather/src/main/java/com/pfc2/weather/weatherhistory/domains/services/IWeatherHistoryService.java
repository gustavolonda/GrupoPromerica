package com.pfc2.weather.weatherhistory.domains.services;

import com.pfc2.weather.weatherhistory.domains.data.WeatherHistoryDto;

public interface IWeatherHistoryService {
	WeatherHistoryDto getByLatAndLon(double lat, double lon);
}
