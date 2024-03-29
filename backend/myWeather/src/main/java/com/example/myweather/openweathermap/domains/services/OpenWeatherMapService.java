package com.example.myweather.openweathermap.domains.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.example.myweather.commons.api.domains.data.UtilMyWeather.getMessage;
import com.example.myweather.commons.api.domains.exception.BaseException;
import com.example.myweather.openweathermap.domains.data.OpenWeatherMapResponse;

import lombok.SneakyThrows;

@Service
public class OpenWeatherMapService {
	private static final String TAG             = OpenWeatherMapService.class.getSimpleName();
	private static final String LAT             = "lat";
	private static final String LON             = "lon";
	private static final String LAT_WITH_SIG    = "{lat}";
	private static final String LON_WITH_SIG    = "{lon}";
	private static final String APP_ID          = "appid";
	private static final String APP_ID_WITH_SIG = "{appid}";
	
	@Value("${open-weather-map.api-key}")
	private  String apiKey;
	
	@Value("${open-weather-map.host}")
	private  String host;
	
	@Value("${open-weather-map.host.api.current-weather}")
	private  String apiCurrentWeather;
	
	@SneakyThrows
	public OpenWeatherMapResponse getByLatAndLon(double lat, double lon) {
		try {
			 RestTemplate restTemplate = new RestTemplate();
			 HttpHeaders headers = new HttpHeaders();
			 headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			 HttpEntity<?> entity = new HttpEntity<>(headers);
			 
			 String url = new StringBuilder(host)
					 				.append(apiCurrentWeather)
					 				.toString();
			 
			 String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
												        .queryParam(LAT, LAT_WITH_SIG)
												        .queryParam(LON, LON_WITH_SIG)
												        .queryParam(APP_ID, APP_ID_WITH_SIG)
												        .encode()
												        .toUriString();

			Map<String, Object> params = new HashMap<>();
			params.put(LAT, lat);
			params.put(LON, lon);
			params.put(APP_ID, apiKey);
	

			HttpEntity<OpenWeatherMapResponse> response = restTemplate.exchange(
																	        urlTemplate,
																	        HttpMethod.GET,
																	        entity,
																	        OpenWeatherMapResponse.class,
																	        params);
			return response.getBody();
		}catch (Exception e) {
			throw new BaseException().builder()
									.message(getMessage("open-weather-map.api.weather-current.error"))
									.module(TAG)
									.exception(e)
									.build();
		}
	}
}
