package com.pfc2.weather.commons.api.infraestructure.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static com.pfc2.weather.commons.api.infraestructure.configs.Constants.HISTORY_URL;
import static com.pfc2.weather.commons.api.infraestructure.configs.Constants.SYS_STATUS_URL;
import static com.pfc2.weather.commons.api.infraestructure.configs.Constants.WEATHER_URL;
import static com.pfc2.weather.commons.api.infraestructure.configs.Constants.SCOPE_READ;
import static com.pfc2.weather.commons.api.infraestructure.configs.Constants.SCOPE_CREATE;
import static com.pfc2.weather.commons.api.infraestructure.configs.Constants.SWAGGER_URL;
import static com.pfc2.weather.commons.api.infraestructure.configs.Constants.API_DOC_SWAGGER_URL;
import static com.pfc2.weather.commons.api.infraestructure.configs.Constants.API_DOC_SWAGGER_SUB_URL;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
	@Autowired
    MvcRequestMatcher.Builder mvc;
	public static AntPathRequestMatcher[] PUBLIC_REQUEST_MATCHERS = { new AntPathRequestMatcher(SYS_STATUS_URL),
																	new AntPathRequestMatcher(API_DOC_SWAGGER_URL), 
																	new AntPathRequestMatcher(API_DOC_SWAGGER_SUB_URL),
																	new AntPathRequestMatcher(SWAGGER_URL)};
	
	@Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    } 
	@Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }
	@Autowired
    private JwtAuthEntryPoint unauthorizedHandler;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
        This is where we configure the security required for our endpoints and setup our app to serve as
        an OAuth2 Resource Server, using JWT validation.
        */
        return http
                .authorizeHttpRequests((authorize) -> authorize
                	.requestMatchers(PUBLIC_REQUEST_MATCHERS).permitAll()	
                    .requestMatchers(mvc.pattern(HttpMethod.GET, WEATHER_URL)).hasAuthority(SCOPE_READ)
                    .requestMatchers(mvc.pattern(HttpMethod.POST, WEATHER_URL)).hasAuthority(SCOPE_CREATE)
                    .requestMatchers(mvc.pattern(HttpMethod.GET, WEATHER_URL + HISTORY_URL)).hasAuthority(SCOPE_READ).and()
           
                )
                
                .cors(withDefaults())
                .oauth2ResourceServer(oauth2 -> oauth2
                    .jwt(withDefaults()))
                .exceptionHandling(x -> x.accessDeniedHandler(unauthorizedHandler)
                							.authenticationEntryPoint(unauthorizedHandler))
                .build();
    }
}
