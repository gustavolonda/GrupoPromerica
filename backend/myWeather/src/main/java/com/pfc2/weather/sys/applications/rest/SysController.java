package com.pfc2.weather.sys.applications.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfc2.weather.commons.api.domains.data.ResponseBase;
import com.pfc2.weather.commons.api.domains.exception.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "Sys", description = "Los apis del sistema")
@RestController
@RequestMapping("/sys")
public class SysController {
	@Operation(
		      summary = "Get status",
		      description = "Muestra el estado del sistema")
		  @ApiResponses({
		      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
		      @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }),
		      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }),
		      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }),
		      @ApiResponse(responseCode = "503", content = { @Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") })
		  })
	@GetMapping("/status")
	public ResponseEntity<?> getStatus() {
		return ResponseEntity.ok().build();
	}
}
