package com.example.myweather.weatherhistory.domains.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.myweather.weatherhistory.infraestructure.entities.WeatherHistoryEntity;
import com.example.myweather.weatherhistory.infraestructure.repositories.WeatherHistoryRepository;

import lombok.extern.slf4j.Slf4j;

import com.example.myweather.commons.api.applications.model.StatusDomain;
import com.example.myweather.commons.api.domains.services.EndPointServiceImpl;

@Service
@Slf4j
public class WeatherHistoryService extends EndPointServiceImpl<WeatherHistoryEntity, String>{
	private static final String TAG  = WeatherHistoryService.class.getSimpleName();
	@Autowired
	WeatherHistoryRepository repository;


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




}
