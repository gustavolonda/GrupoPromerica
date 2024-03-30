package com.example.myweather.weatherhistory.infraestructure.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myweather.weatherhistory.infraestructure.entities.WeatherHistoryEntity;

@Repository
public interface WeatherHistoryRepository extends JpaRepository<WeatherHistoryEntity, String>{

}
