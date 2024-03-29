package com.example.myweather.commons.api.infraestructure.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.beans.factory.annotation.Value;
import static com.example.myweather.commons.api.infraestructure.configs.Constants.*;

import org.springframework.validation.Validator;
@Configuration
@RefreshScope
public class LanguageConfig {
    @Value("${myWeather.language}")
    String language;

    @Bean
    @RefreshScope
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:"+"messages"+SEPARATE+language);
        messageSource.setCacheSeconds(10);
        return messageSource;
    }

    public LocalValidatorFactoryBean validator()
    {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }


    public Validator getValidator()
    {
        return validator();
    }

}
