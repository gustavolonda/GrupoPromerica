package com.pfc2.weather.weatherhistory.applications.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.pfc2.weather.commons.api.applications.model.StatusResponse.OK;
import com.pfc2.weather.commons.api.domains.data.ResponseBase;
import com.pfc2.weather.commons.api.domains.exception.ErrorResponse;

import static com.pfc2.weather.commons.api.domains.data.UtilMyWeather.getMessage;

import com.pfc2.weather.weatherhistory.domains.data.WeatherHistoryDto;
import com.pfc2.weather.weatherhistory.domains.services.WeatherHistoryServiceImpl;
import com.pfc2.weather.weatherhistory.infraestructure.mappers.IWeatherHistoryMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
@Tag(name = "Weather", description = "Los apis del clima")
@RestController
@RequestMapping("/api/v1/weather")
public class WeatherHistoryController {
	@Autowired
	WeatherHistoryServiceImpl service;
	@Autowired
	private IWeatherHistoryMapper iWeatherHistoryMapper;
	@Operation(
		      summary = "Get by Lat and Lon",
		      description = "Obtiene el clima actulmendiante la latitud y la longitud de un lugar")
		  @ApiResponses({
		      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ResponseBase.class), mediaType = "application/json") }),
		      @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }),
		      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }),
		      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }),
		      @ApiResponse(responseCode = "503", content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") })
		  })
	@GetMapping
	public ResponseEntity<?> getByLatAndLon(@RequestParam("lat") double lat, 
											@RequestParam("lon") double lon) {
		return ResponseEntity.ok()
							.body(new ResponseBase().builder()
													.status(OK.getValue())
													.message(getMessage("my-weather.api.response.ok"))
													.result(service.getByLatAndLon(lat, lon))
													.build());
	}
	@Operation(
		      summary = "Save Lat and Lon",
		      description = "Guarda la latitud y longitud")
		  @ApiResponses({
		      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ResponseBase.class), mediaType = "application/json") }),
		      @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }),
		      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }),
		      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }),
		      @ApiResponse(responseCode = "503", content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") })
		  })
	@PostMapping
	public ResponseEntity<?> save(@RequestBody WeatherHistoryDto weatherHistoryDto ) {
		return ResponseEntity.ok()
							.body(new ResponseBase().builder()
													.status(OK.getValue())
													.message(getMessage("my-weather.api.response.ok"))
													.result(iWeatherHistoryMapper.toWeatherHistoryDto(service.save(iWeatherHistoryMapper.toWeatherHistoryEntity(weatherHistoryDto))))
													.build());
	}
	@Operation(
		      summary = "Get history",
		      description = "Muestra todos los datos")
		  @ApiResponses({
		      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ResponseBase.class), mediaType = "application/json") }),
		      @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }),
		      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }),
		      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }),
		      @ApiResponse(responseCode = "503", content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") })
		  })
	@GetMapping("/history")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok()
							.body(new ResponseBase().builder()
													.status(OK.getValue())
													.message(getMessage("my-weather.api.response.ok"))
													.result(iWeatherHistoryMapper.toWeatherHistoryDto(service.getAll()))
													.build());
	}

}
