package com.pfc2.weather.weatherhistory.infraestructure.repositories;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfc2.weather.weatherhistory.infraestructure.entities.WeatherHistoryEntity;

@Repository
public interface WeatherHistoryRepository extends JpaRepository<WeatherHistoryEntity, String>{
	WeatherHistoryEntity findFirstByLatAndLonAndQueryServerDateGreaterThanEqual(double lat, double lon, Date queryServerDate);
	WeatherHistoryEntity findFirstByLatAndLon(double lat, double lon);

}
