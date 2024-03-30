package com.example.myweather.weatherhistory.infraestructure.entities;
import com.example.myweather.commons.api.infraestructure.entities.*;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
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
	private String  weather;
	@Column(name = "temp_min")
	private double tempMin;
	@Column(name = "temp_max")
	private double tempMax;
	private int humidity;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "query_server_date")
	private Date queryServerDate;
}
