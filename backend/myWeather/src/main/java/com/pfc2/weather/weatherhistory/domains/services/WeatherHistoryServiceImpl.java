package com.pfc2.weather.weatherhistory.domains.services;

import static com.pfc2.weather.commons.api.domains.data.UtilMyWeather.dateAddMinutes;
import static com.pfc2.weather.commons.api.domains.data.UtilMyWeather.getMessage;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Service;

import com.pfc2.weather.commons.api.applications.model.StatusDomain;
import com.pfc2.weather.commons.api.domains.exception.BaseException;
import com.pfc2.weather.commons.api.domains.services.EndPointServiceImpl;
import com.pfc2.weather.openweathermap.domains.data.OpenWeatherMapDto;
import com.pfc2.weather.openweathermap.domains.services.OpenWeatherMapService;
import com.pfc2.weather.weatherhistory.domains.data.WeatherHistoryDto;
import com.pfc2.weather.weatherhistory.infraestructure.entities.WeatherHistoryEntity;
import com.pfc2.weather.weatherhistory.infraestructure.mappers.IWeatherHistoryMapper;
import com.pfc2.weather.weatherhistory.infraestructure.repositories.WeatherHistoryRepository;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class WeatherHistoryServiceImpl extends EndPointServiceImpl<WeatherHistoryEntity, String> implements IWeatherHistoryService{
	private static final String TAG  = WeatherHistoryServiceImpl.class.getSimpleName();
	@Autowired
	private WeatherHistoryRepository repository;
	@Autowired
	private OpenWeatherMapService openWeatherMapService;
	@Autowired
	private IWeatherHistoryMapper iWeatherHistoryMapper;
	int TIME_LIMIT = -10; 
	
	
	
	@Override
	public JpaRepository<WeatherHistoryEntity, String> getDao() {
		return this.repository;
	}

	@Override
	public WeatherHistoryEntity statusChangeDelete(WeatherHistoryEntity entity) {
		entity.setStatus(StatusDomain.DELETE.getValue());
		return entity;
	}

	@Override
	public String nameModule() {
		return TAG;
	}

	@SneakyThrows
	@Override
	public WeatherHistoryDto getByLatAndLon(double lat, double lon) {
		try {
			Date dateLimit = dateAddMinutes(new Date(), TIME_LIMIT);
			WeatherHistoryEntity weatherHistoryEntity =	repository.findFirstByLatAndLonAndQueryServerDateGreaterThanEqual(lat, lon,dateLimit);		
			if (weatherHistoryEntity != null) {
				return iWeatherHistoryMapper.toWeatherHistoryDto(weatherHistoryEntity);
			}
			IWeatherHistoryMapper.INSTANCE.toWeatherHistoryDto(weatherHistoryEntity);
			OpenWeatherMapDto openWeatherMapDto = openWeatherMapService.getByLatAndLon(lat, lon);
			WeatherHistoryDto weatherHistoryDto = iWeatherHistoryMapper.toWeatherHistoryDto(openWeatherMapDto);
			WeatherHistoryEntity weatherHistoryEntityNew = iWeatherHistoryMapper.toWeatherHistoryEntity(weatherHistoryDto);
			weatherHistoryEntity =	repository.findFirstByLatAndLon(lat, lon);
			
			if (weatherHistoryEntity != null) {
				weatherHistoryEntity.setWeather(weatherHistoryEntityNew.getWeather());
				weatherHistoryEntity.setTempMin(weatherHistoryEntityNew.getTempMin());
				weatherHistoryEntity.setTempMax(weatherHistoryEntityNew.getTempMax());	
				weatherHistoryEntity.setHumidity(weatherHistoryEntityNew.getHumidity());
				weatherHistoryEntity.setQueryServerDate(new Date());
				weatherHistoryEntityNew = this.update(weatherHistoryEntity);
				return iWeatherHistoryMapper.toWeatherHistoryDto(weatherHistoryEntityNew);
			}
			
			weatherHistoryEntityNew.setQueryServerDate(new Date());
			weatherHistoryEntityNew = this.save(weatherHistoryEntityNew);	
			return iWeatherHistoryMapper.toWeatherHistoryDto(weatherHistoryEntityNew);
			
		}catch (Exception e) {
			BaseException baseException= new BaseException().builder()
															.message(getMessage("open-weather-map.api.weather-current.error"))
															.module(TAG)
															.exception(e)
															.build();
			log.info("open-weather-map.api.weather-current.error",baseException);
			throw baseException;
		}
	}

	@Override
	public BaseException validate(WeatherHistoryEntity entity) {
		WeatherHistoryEntity weatherHistoryEntity =	repository.findFirstByLatAndLon(entity.getLat(), entity.getLon());
		if(weatherHistoryEntity != null) {
			Exception e = new Exception();
			return new BaseException().builder()
					.message(getMessage("open-weather-map.api.save.error.duplicate"))
					.module(TAG)
					.exception(e)
					.build();
		}
		return null;
	}

	


}
