package com.pfc2.weather.weatherhistory.infraestructure.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;


import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.pfc2.weather.commons.api.infraestructure.entities.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "weather_history")
public class WeatherHistoryEntity extends BaseEntity{
	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "text")
    private String id;
	private double lat;
	private double lon;
	private String  weather;
	@Column(name = "temp_min")
	private double tempMin;
	@Column(name = "temp_max")
	private double tempMax;
	private int humidity;
	@Column(name = "query_server_date" , columnDefinition = "TIMESTAMP")
	private Date queryServerDate;
}
