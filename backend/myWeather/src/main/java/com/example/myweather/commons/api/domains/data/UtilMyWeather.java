package com.example.myweather.commons.api.domains.data;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilMyWeather {
	@Autowired
    private MessageSource messageSource;
	
	private static MessageSource estaticMessageSource;
	
	@Bean
    public void UtilMyWeather(){
     estaticMessageSource = messageSource;
    };
	public static String getMessage(String property){
        return estaticMessageSource.getMessage(property, null,Locale.getDefault());
    }

}
