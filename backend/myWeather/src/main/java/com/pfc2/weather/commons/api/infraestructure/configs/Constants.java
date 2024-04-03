package com.pfc2.weather.commons.api.infraestructure.configs;

public class Constants {
	public static String SEPARATE        = "-";
	public static final String API_VERSION_URL = "/api/v1";
	public static final String WEATHER_URL     = API_VERSION_URL + "/weather";
	public static final String HISTORY_URL     =  "/history";
	public static String SYS_STATUS_URL  = "/sys/status";
	public static String SWAGGER_URL     = "/swagger-ui/*";
	public static String API_DOC_SWAGGER_URL = "/v3/api-docs";
	public static String API_DOC_SWAGGER_SUB_URL = API_DOC_SWAGGER_URL + "/*";
	public static String SCOPE_READ      = "SCOPE_read:client_credentials";
	public static String SCOPE_CREATE    = "SCOPE_create:client_credentials";
				
}
