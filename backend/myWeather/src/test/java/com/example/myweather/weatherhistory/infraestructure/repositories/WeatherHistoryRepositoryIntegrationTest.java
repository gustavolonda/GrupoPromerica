package com.example.myweather.weatherhistory.infraestructure.repositories;

import static  com.pfc2.weather.commons.api.domains.data.UtilMyWeather.toDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.pfc2.weather.weatherhistory.infraestructure.entities.WeatherHistoryEntity;
import com.pfc2.weather.weatherhistory.infraestructure.repositories.WeatherHistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;



import java.text.SimpleDateFormat;
import java.util.Date;

@ContextConfiguration(classes = { WeatherHistoryRepository.class })
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"com.pfc2.weather.*"})
@EntityScan(basePackages = { "com.pfc2.weather.*"})
@DataJpaTest
public class WeatherHistoryRepositoryIntegrationTest {
    @Autowired
    WeatherHistoryRepository weatherHistoryRepository;

    @Test
    void testSave() {
        // Given
        WeatherHistoryEntity weatherHistoryEntity = WeatherHistoryEntity.builder()
                                                                        .lat(10.01)
                                                                        .lon(-84.1)
                                                                        .weather("Fog")
                                                                        .tempMin(291.83)
                                                                        .tempMax(294.0)
                                                                        .humidity(87)
                                                                        .queryServerDate(toDate("05-04-2024 02:19 AM UTC"))
                                                                        .build();

        // When
        WeatherHistoryEntity weatherHistoryEntityResponse = weatherHistoryRepository.save(weatherHistoryEntity);

        // Then
        assertNotNull(weatherHistoryEntityResponse.getId());
        assertEquals(10.01, weatherHistoryEntityResponse.getLat());
        assertEquals(-84.1, weatherHistoryEntityResponse.getLon());
        assertNotNull(weatherHistoryEntityResponse.getCreateDate());
    }
}
