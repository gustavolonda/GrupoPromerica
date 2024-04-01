package com.example.myweather.openweathermap.domains.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sys {
	private int type;
    private int id;
    private String country;
    private int sunrise;
    private int sunset;
}
