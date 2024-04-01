package com.pfc2.weather.openweathermap.domains.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wind {
	private double speed;
    private int deg;
    private double gust;
}
