package com.example.myweather.weatherhistory.infraestructure.repositories;

import static com.example.myweather.weatherhistory.infraestructure.configs.Constants.TAG_INGRATETION_TEST_RESPOSITORY;
import static  com.pfc2.weather.commons.api.domains.data.UtilMyWeather.toDate;
import static org.junit.jupiter.api.Assertions.*;

import com.pfc2.weather.weatherhistory.infraestructure.entities.WeatherHistoryEntity;
import com.pfc2.weather.weatherhistory.infraestructure.repositories.WeatherHistoryRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;



import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;
import java.util.List;

@ContextConfiguration(classes = { WeatherHistoryRepository.class })
@EnableJpaRepositories(basePackages = {"com.pfc2.weather.*"})
@EntityScan(basePackages = { "com.pfc2.weather.*"})
@DataJpaTest
public class WeatherHistoryRepositoryIntegrationTest {
    @Autowired
    WeatherHistoryRepository weatherHistoryRepository;

    @Test
    void givenWeatherHistoryEntity_whenSaveWeatherHistoryEntity_thenReturnWeatherHistoryEntity() {
        // Given
        WeatherHistoryEntity weatherHistoryEntity = WeatherHistoryEntity.builder()
                                                                        .lat(10.01)
                                                                        .lon(-84.1)
                                                                        .weather("Fog")
                                                                        .tempMin(291.83)
                                                                        .tempMax(294.0)
                                                                        .humidity(87)
                                                                        .createDate(new Date())
                                                                        .modifyDate(new Date())
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

    // JUnit test for findAll method
    @DisplayName("JUnit test for findAll method")
    @Test
    public void whenGetAllWeatherHistoryEntities_thenReturnWeatherHistoryEntitiesList(){
        // When
        List<WeatherHistoryEntity> weatherHistoryEntities = weatherHistoryRepository.findAll();


        // Then
        assertEquals(0, weatherHistoryEntities.spliterator().getExactSizeIfKnown());
        assertEquals(10.01, ((WeatherHistoryEntity) weatherHistoryEntities.iterator().next()).getLat());
        assertEquals(-84.1, ((WeatherHistoryEntity) weatherHistoryEntities.iterator().next()).getLon());
    }

    @DisplayName("JUnit test for findAll method (negative scenario)")
    @Test
    public void whenGetAllWeatherHistoryEntities_thenReturnEmptyWeatherHistoryEntitiesList(){

        // When
        List<WeatherHistoryEntity> weatherHistoryEntities = weatherHistoryRepository.findAll();

        // then - verify the output
        assertThat(weatherHistoryEntities).isEmpty();
        assertThat(weatherHistoryEntities.size()).isEqualTo(0);
    }

    // JUnit test for findById method
    @DisplayName("JUnit test for findById method")
    @Test
    public void givenWeatherHistoryId_whenGetEmployeeById_thenReturnWeatherHistoryEntity(){
        // Given
        String weatherHistoryId = "8e0144c4-7ed9-4d7b-beab-702d89bbc627";

        // When
        WeatherHistoryEntity weatherHistoryEntity = weatherHistoryRepository.findById(weatherHistoryId).orElse(null);

        // Then
        assertNotNull(weatherHistoryEntity);
        assertEquals(weatherHistoryId, weatherHistoryEntity.getId());
    }
}
