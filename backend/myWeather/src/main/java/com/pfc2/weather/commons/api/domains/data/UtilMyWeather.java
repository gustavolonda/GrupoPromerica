package com.pfc2.weather.commons.api.domains.data;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;

@Configuration
public class UtilMyWeather {
	@Autowired
	private MessageSource messageSource;

	private static MessageSource estaticMessageSource;
	
	public static final String  FORMAT_DATE = "dd-MM-yyyy HH:mm a z";

	@Bean
	public void UtilMyWeather() {
		estaticMessageSource = messageSource;
	};

	public static String getMessage(String property) {
		return estaticMessageSource.getMessage(property, null, Locale.getDefault());
	}

	public static Date dateAddMinutes(Date date, int minutes) {
		// add X minutes
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime());
		cal.add(Calendar.MINUTE, minutes);
		date = new Date(cal.getTime().getTime());
		return date;
	}

	public static Object jsonToObject(String cad, Class tclass){
        return new Gson().fromJson(cad, tclass);
    }
	public static String toJson(Object object){
        return new Gson().toJson(object);
    }

}
