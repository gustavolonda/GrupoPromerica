package com.pfc2.weather.weatherhistory.infraestructure.mappers;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.pfc2.weather.openweathermap.domains.data.OpenWeatherMapDto;
import com.pfc2.weather.weatherhistory.domains.data.WeatherHistoryDto;
import com.pfc2.weather.weatherhistory.infraestructure.entities.WeatherHistoryEntity;

@Mapper(componentModel = "spring")
public interface IWeatherHistoryMapper {
	IWeatherHistoryMapper INSTANCE = Mappers.getMapper(IWeatherHistoryMapper.class);
	WeatherHistoryDto toWeatherHistoryDto (WeatherHistoryEntity entity);
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "lat", source = "coord.lat")
	@Mapping(target = "lon", source = "coord.lon")
	@Mapping(expression = "java(openWeatherMapDto.getWeather().get(0).getMain())", target = "weather")
	@Mapping(target = "tempMin", source = "main.tempMin")
	@Mapping(target = "tempMax", source = "main.tempMax")
	@Mapping(target = "humidity", source = "main.humidity")
	@Mapping(target = "queryServerDate", ignore = true)
	@Mapping(target = "createDate", ignore = true)
	@Mapping(target = "modifyDate", ignore = true)
	@Mapping(target = "status", ignore = true)
	WeatherHistoryDto toWeatherHistoryDto (OpenWeatherMapDto openWeatherMapDto);
	WeatherHistoryEntity toWeatherHistoryEntity ( WeatherHistoryDto weatherHistoryDto);
	List<WeatherHistoryDto> toWeatherHistoryDto (List<WeatherHistoryEntity> entityList);
}
